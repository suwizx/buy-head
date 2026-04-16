package dev.suwizx.buyhead.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import dev.suwizx.buyhead.config.BuyHeadConfig
import dev.suwizx.buyhead.gui.GuiManager
import dev.suwizx.buyhead.head.SkinResolver
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.permissions.Permission
import net.minecraft.server.permissions.PermissionLevel
import org.slf4j.LoggerFactory

object BuyHeadCommand {

    private val LOGGER = LoggerFactory.getLogger("buy-head")

    // Requires op level 2 (gamemasters) to use /buyhead reload
    private val OP_LEVEL_2: Permission = Permission.HasCommandLevel(PermissionLevel.GAMEMASTERS)

    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            Commands.literal("buyhead")
                .requires { src -> src.isPlayer }
                .executes { ctx ->
                    openMain(ctx.source.playerOrException, ctx.source.server)
                    1
                }
                .then(
                    Commands.literal("player")
                        .then(
                            Commands.argument("username", StringArgumentType.word())
                                .executes { ctx ->
                                    openCustomPlayerHead(
                                        ctx.source.playerOrException,
                                        ctx.source.server,
                                        StringArgumentType.getString(ctx, "username")
                                    )
                                    1
                                }
                        )
                )
                .then(
                    Commands.literal("reload")
                        .requires { src -> src.permissions().hasPermission(OP_LEVEL_2) }
                        .executes { ctx ->
                            BuyHeadConfig.reload()
                            ctx.source.sendSuccess(
                                { Component.literal("§aBuyHead config reloaded.") },
                                true
                            )
                            1
                        }
                )
        )
    }

    private fun openMain(player: ServerPlayer, server: MinecraftServer) {
        LOGGER.debug("[BuyHead] {} opened main menu", player.name.string)
        GuiManager.openMainMenu(player, server)
    }

    private fun openCustomPlayerHead(player: ServerPlayer, server: MinecraftServer, username: String) {
        if (!BuyHeadConfig.current.allowCustomPlayerHead) {
            player.sendSystemMessage(Component.literal("§cCustom player heads are disabled on this server."))
            return
        }

        LOGGER.debug("[BuyHead] {} requested head for player '{}'", player.name.string, username)
        player.sendSystemMessage(Component.literal("§7Looking up §f$username§7's skin..."))

        SkinResolver.resolveAsync(username, server)
            .thenAccept { opt ->
                if (opt.isPresent) {
                    GuiManager.openConfirmMenu(player, opt.get())
                } else {
                    LOGGER.info("[BuyHead] Skin lookup returned empty for '{}'", username)
                    player.sendSystemMessage(Component.literal("§cPlayer '§f$username§c' not found."))
                }
            }
            .exceptionally { ex ->
                LOGGER.error("[BuyHead] Unexpected error during skin lookup for '{}' (requested by {})", username, player.name.string, ex)
                player.sendSystemMessage(Component.literal("§c[BuyHead] Skin lookup failed. Check server logs."))
                null
            }
    }
}
