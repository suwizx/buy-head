package dev.suwizx.buyhead.head

import com.google.gson.JsonParser
import net.minecraft.server.MinecraftServer
import net.minecraft.util.Util
import org.slf4j.LoggerFactory
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

object HeadDbClient {

    private val LOGGER = LoggerFactory.getLogger("buy-head")
    private const val BASE_URL = "https://headdb.org/api/category/"
    private const val MEM_TTL_MS = 5 * 60 * 1000L  // 5 minutes

    data class HeadEntry(val name: String, val value: String)

    private data class CachedData(val entries: List<HeadEntry>, val fetchedAt: Long)

    private val memCache = ConcurrentHashMap<String, CachedData>()

    private val http: HttpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build()

    /**
     * Fetches one category from headdb.org. Returns the in-memory cached result immediately if
     * it is still fresh (< 5 min). Otherwise runs the HTTP call on [Util.backgroundExecutor]
     * and delivers the result on the server thread — identical pattern to SkinResolver.
     */
    fun fetchCategory(category: String, server: MinecraftServer): CompletableFuture<List<HeadEntry>> {
        val cached = memCache[category]
        if (cached != null && System.currentTimeMillis() - cached.fetchedAt < MEM_TTL_MS) {
            return CompletableFuture.completedFuture(cached.entries)
        }

        return CompletableFuture
            .supplyAsync({ fetchSync(category) }, Util.backgroundExecutor())
            .thenApplyAsync({ entries ->
                memCache[category] = CachedData(entries, System.currentTimeMillis())
                entries
            }, server)
    }

    /**
     * Fetches all [categories] in parallel. Per-category failures fall back to
     * [HeadCache.getFile] and then to an empty list — the menu still opens.
     */
    fun fetchAllCategories(
        categories: List<String>,
        server: MinecraftServer
    ): CompletableFuture<Map<String, List<HeadEntry>>> {
        val futures = categories.map { cat ->
            fetchCategory(cat, server)
                .thenApply { entries -> cat to entries }
                .exceptionally { ex ->
                    LOGGER.warn("[BuyHead] HeadDB fetch failed for category '{}': {}", cat, ex.message)
                    cat to (HeadCache.getFile(cat) ?: emptyList())
                }
        }
        return CompletableFuture.allOf(*futures.toTypedArray())
            .thenApplyAsync({ futures.associate { it.join() } }, server)
    }

    private fun fetchSync(category: String): List<HeadEntry> {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("$BASE_URL$category"))
            .timeout(Duration.ofSeconds(15))
            .header("User-Agent", "BuyHead-Fabric-Mod")
            .GET()
            .build()

        val response = http.send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() != 200) {
            throw RuntimeException("HTTP ${response.statusCode()} for category $category")
        }

        // HeadDB returns a JSON object keyed by UUID: { "uuid": { name, uuid, value, ... }, ... }
        val root = JsonParser.parseString(response.body())
        if (!root.isJsonObject) {
            LOGGER.warn("[BuyHead] HeadDB response for {} is not a JSON object", category)
            return emptyList()
        }
        
        val entries = mutableListOf<HeadEntry>()
        val jsonObj = root.asJsonObject
        for ((_, element) in jsonObj.entrySet()) {
            if (!element.isJsonObject) continue
            val obj = element.asJsonObject
            val name = obj.get("name")?.asString ?: continue
            val value = obj.get("value")?.asString ?: continue
            entries.add(HeadEntry(name, value))
        }
        return entries
    }

    fun clearMemCache() { memCache.clear() }
}
