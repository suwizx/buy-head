package dev.suwizx.buyhead.head

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dev.suwizx.buyhead.config.CategoryConfig
import dev.suwizx.buyhead.config.DefaultHeads
import dev.suwizx.buyhead.config.HeadDefinition
import net.minecraft.util.Util
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.readText

object HeadCache {

    private val LOGGER = LoggerFactory.getLogger("buy-head")
    private val GSON = GsonBuilder().setPrettyPrinting().create()
    private val TYPE = object : TypeToken<Map<String, List<HeadDbClient.HeadEntry>>>() {}.type
    private const val CACHE_PER_CATEGORY = 10

    /** Populated by [loadFromDisk] at server startup. */
    @Volatile
    private var fileData: Map<String, List<HeadDbClient.HeadEntry>> = emptyMap()

    /** Loads `config/buy-head-heads-cache.json` into memory. Call from [BuyHead.onInitialize]. */
    fun loadFromDisk(configDir: Path) {
        val file = configDir.resolve("buy-head-heads-cache.json")
        if (!file.exists()) return
        try {
            fileData = GSON.fromJson(file.readText(), TYPE) ?: emptyMap()
            LOGGER.info("[BuyHead] Loaded head cache: {} categories", fileData.size)
        } catch (e: Exception) {
            LOGGER.warn("[BuyHead] Failed to load head cache — will re-fetch from API", e)
        }
    }

    /**
     * Saves the top-[CACHE_PER_CATEGORY] entries per category to disk atomically.
     * Runs on [Util.backgroundExecutor] — never blocks the server thread.
     */
    fun saveToDisk(configDir: Path, data: Map<String, List<HeadDbClient.HeadEntry>>) {
        Util.backgroundExecutor().execute {
            try {
                val trimmed = data.mapValues { (_, heads) -> heads.take(CACHE_PER_CATEGORY) }
                configDir.createDirectories()
                val file = configDir.resolve("buy-head-heads-cache.json")
                val tmp = file.resolveSibling("buy-head-heads-cache.json.tmp")
                tmp.toFile().writeText(GSON.toJson(trimmed, TYPE))
                Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE)
                fileData = trimmed  // update in-memory view of file
                LOGGER.debug("[BuyHead] Head cache saved ({} categories)", trimmed.size)
            } catch (e: Exception) {
                LOGGER.warn("[BuyHead] Failed to save head cache", e)
            }
        }
    }

    /** Returns cached entries for [category] from the on-disk data, or null if absent. */
    fun getFile(category: String): List<HeadDbClient.HeadEntry>? = fileData[category]

    /**
     * Converts [categories] from the file cache into [CategoryConfig] list for the GUI.
     * Falls back to [DefaultHeads.categories] if the file cache has no data.
     */
    fun toCategories(categories: List<String>): List<CategoryConfig> {
        if (fileData.isEmpty()) {
            LOGGER.debug("[BuyHead] Head cache empty — falling back to default heads")
            return DefaultHeads.categories()
        }
        return categories.mapNotNull { id ->
            val heads = fileData[id] ?: return@mapNotNull null
            if (heads.isEmpty()) return@mapNotNull null
            val displayName = id.split("-")
                .joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
            CategoryConfig(
                id = id,
                displayName = displayName,
                iconTexture = heads.first().value,
                heads = heads.map { HeadDefinition(it.name, it.value) }
            )
        }.ifEmpty { DefaultHeads.categories() }
    }
}
