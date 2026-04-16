package dev.suwizx.buyhead

import dev.suwizx.buyhead.command.BuyHeadCommand
import dev.suwizx.buyhead.config.BuyHeadConfig
import dev.suwizx.buyhead.gui.GuiStateManager
import dev.suwizx.buyhead.head.HeadCache
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.fabricmc.loader.api.FabricLoader
import org.slf4j.LoggerFactory

object BuyHead : ModInitializer {
    const val MOD_ID = "buy-head"
    private val logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        val configDir = FabricLoader.getInstance().configDir
        BuyHeadConfig.load(configDir)
        HeadCache.loadFromDisk(configDir)
        logger.info("BuyHead loaded — currency: ${BuyHeadConfig.current.currencyAmount}x ${BuyHeadConfig.current.currencyItem}")

        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            BuyHeadCommand.register(dispatcher)
        }

        // Clean up GUI state when a player disconnects
        ServerPlayConnectionEvents.DISCONNECT.register { handler, _ ->
            GuiStateManager.remove(handler.player.uuid)
        }
    }
}
