package dev.suwizx.buyhead.config

import com.google.gson.GsonBuilder
import org.slf4j.LoggerFactory
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.readText

data class HeadDefinition(
    var name: String = "",
    var texture: String = ""
)

data class CategoryConfig(
    var id: String = "",
    var displayName: String = "",
    var iconTexture: String = "",
    var heads: List<HeadDefinition> = emptyList()
)

// Uses `var` so Gson can deserialize without final-field reflection warnings on Java 21+
data class Config(
    var currencyItem: String = "minecraft:emerald",
    var currencyAmount: Int = 64,
    var allowCustomPlayerHead: Boolean = true,
    var customHeadCooldownSeconds: Int = 30,
    var useHeadDbApi: Boolean = true,
    var headDbCategories: List<String> = listOf("blocks", "characters", "christmas", "electronics", "flags", "food", "halloween", "letters", "youtubers"),
    var categories: List<CategoryConfig> = DefaultHeads.categories()
)

object BuyHeadConfig {
    private val LOGGER = LoggerFactory.getLogger("buy-head")
    private val GSON = GsonBuilder().setPrettyPrinting().create()

    private lateinit var configFile: Path
    var current: Config = Config()
        private set

    fun load(configDir: Path) {
        configFile = configDir.resolve("buy-head.json")
        if (!configFile.exists()) {
            save()
            LOGGER.info("Created default config at $configFile")
            return
        }
        try {
            current = GSON.fromJson(configFile.readText(), Config::class.java) ?: Config()
            LOGGER.info("Loaded config: ${current.currencyAmount}x ${current.currencyItem}")
        } catch (e: Exception) {
            LOGGER.error("Failed to load config, using defaults", e)
            current = Config()
        }
    }

    fun save() {
        configFile.parent?.createDirectories()
        // Write to a temp file first, then atomically move — prevents corrupt config on crash/partial write
        val tmp = configFile.resolveSibling("buy-head.json.tmp")
        tmp.toFile().writeText(GSON.toJson(current))
        Files.move(tmp, configFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE)
    }

    fun reload() {
        load(configFile.parent)
    }
}
