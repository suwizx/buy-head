package dev.suwizx.buyhead.head

import com.mojang.authlib.GameProfile
import net.minecraft.server.MinecraftServer
import net.minecraft.util.Util
import org.slf4j.LoggerFactory
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ConcurrentHashMap

object SkinResolver {

    private val LOGGER = LoggerFactory.getLogger("buy-head")
    private const val CACHE_TTL_MS = 10 * 60 * 1000L

    private data class CachedProfile(val profile: GameProfile, val fetchedAt: Long)

    private val cache = ConcurrentHashMap<String, CachedProfile>()

    /**
     * Resolves a Minecraft username to a GameProfile with textures populated.
     * All blocking HTTP calls run on [Util.backgroundExecutor].
     * The returned future completes back on the server's executor (tick-thread safe).
     */
    fun resolveAsync(username: String, server: MinecraftServer): CompletableFuture<Optional<GameProfile>> {
        val key = username.lowercase()
        val cached = cache[key]
        if (cached != null && System.currentTimeMillis() - cached.fetchedAt < CACHE_TTL_MS) {
            return CompletableFuture.completedFuture(Optional.of(cached.profile))
        }

        return CompletableFuture
            .supplyAsync({ fetchProfile(username, server) }, Util.backgroundExecutor())
            .thenApplyAsync({ opt ->
                opt.ifPresent { profile ->
                    cache[key] = CachedProfile(profile, System.currentTimeMillis())
                }
                opt
            }, server)
    }

    private fun fetchProfile(username: String, server: MinecraftServer): Optional<GameProfile> {
        return try {
            // Step 1: resolve username → UUID via ProfileResolver (hits api.mojang.com)
            val resolved: Optional<GameProfile> = server.services().profileResolver().fetchByName(username)
            if (resolved.isEmpty) {
                LOGGER.info("[BuyHead] Player not found: $username")
                return Optional.empty()
            }
            val profile = resolved.get()

            // Step 2: fill textures via session service (hits sessionserver.mojang.com)
            val result = server.services().sessionService().fetchProfile(profile.id, true)
            if (result != null) {
                Optional.of(result.profile())
            } else {
                // Return profile without textures — renders as Steve/Alex
                Optional.of(profile)
            }
        } catch (e: Exception) {
            LOGGER.warn("[BuyHead] Failed to resolve skin for $username", e)
            Optional.empty()
        }
    }

    fun clearCache() { cache.clear() }
    fun invalidate(username: String) { cache.remove(username.lowercase()) }
}
