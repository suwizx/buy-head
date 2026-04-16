package dev.suwizx.buyhead.gui

import com.mojang.authlib.GameProfile
import dev.suwizx.buyhead.config.BuyHeadConfig
import dev.suwizx.buyhead.config.CategoryConfig
import dev.suwizx.buyhead.config.HeadDefinition
import dev.suwizx.buyhead.head.HeadCache
import dev.suwizx.buyhead.head.HeadDbClient
import dev.suwizx.buyhead.head.HeadItem
import dev.suwizx.buyhead.transaction.HeadTransaction
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.network.chat.Component
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import org.slf4j.LoggerFactory

object GuiManager {

    private val LOGGER = LoggerFactory.getLogger("buy-head")

    // -------------------------------------------------------------------------
    // Open menu entry points
    // -------------------------------------------------------------------------

    fun openMainMenu(player: ServerPlayer, server: MinecraftServer) {
        if (BuyHeadConfig.current.useHeadDbApi) {
            openMainMenuFromApi(player, server)
        } else {
            openMainMenuSync(player, liveCategories = null)
        }
    }

    /**
     * Fetches all configured HeadDB categories in parallel (same async pattern as
     * /buyhead player <name>), then opens the main menu on the server thread.
     */
    private fun openMainMenuFromApi(player: ServerPlayer, server: MinecraftServer) {
        val config = BuyHeadConfig.current
        player.sendSystemMessage(Component.literal("§7Fetching heads from HeadDB..."))

        HeadDbClient.fetchAllCategories(config.headDbCategories, server)
            .thenAccept { data ->
                // Persist top-10 per category for offline fallback
                HeadCache.saveToDisk(FabricLoader.getInstance().configDir, data)
                val cats = toCategoryConfigs(data)
                openMainMenuSync(player, liveCategories = cats)
            }
            .exceptionally { ex ->
                LOGGER.error("[BuyHead] HeadDB fetch failed for {}", player.name.string, ex)
                player.sendSystemMessage(Component.literal("§c[BuyHead] Could not reach HeadDB — using cached heads."))
                val cats = HeadCache.toCategories(config.headDbCategories)
                openMainMenuSync(player, liveCategories = cats)
                null
            }
    }

    private fun openMainMenuSync(player: ServerPlayer, liveCategories: List<CategoryConfig>?) {
        try {
            val state = GuiStateManager.open(player.uuid, GuiPageState.Screen.MAIN_MENU).also {
                it.liveCategories = liveCategories
            }
            openMenu(player, state, 6) { MenuBuilder.buildMainMenu(state) }
        } catch (e: Exception) {
            LOGGER.error("[BuyHead] Failed to open main menu for ${player.name.string}", e)
            player.sendSystemMessage(Component.literal("§c[BuyHead] Failed to open menu. Check server logs."))
        }
    }

    fun openCategoryMenu(player: ServerPlayer, categoryId: String, page: Int = 0) {
        try {
            val state = GuiStateManager.get(player.uuid)?.also {
                it.screen = GuiPageState.Screen.CATEGORY
                it.currentCategoryId = categoryId
                it.currentPage = page
                // liveCategories is preserved from the main-menu fetch
            } ?: GuiStateManager.open(player.uuid, GuiPageState.Screen.CATEGORY).also {
                it.currentCategoryId = categoryId
                it.currentPage = page
            }
            openMenu(player, state, 6) { MenuBuilder.buildCategoryMenu(state) }
        } catch (e: Exception) {
            LOGGER.error("[BuyHead] Failed to open category menu '$categoryId' for ${player.name.string}", e)
            player.sendSystemMessage(Component.literal("§c[BuyHead] Failed to open category. Check server logs."))
        }
    }

    fun openConfirmMenu(player: ServerPlayer, profile: GameProfile) {
        try {
            val state = GuiStateManager.get(player.uuid)?.also {
                it.screen = GuiPageState.Screen.CONFIRM_PURCHASE
                it.pendingProfile = profile
                it.pendingHead = null
            } ?: GuiStateManager.open(player.uuid, GuiPageState.Screen.CONFIRM_PURCHASE).also {
                it.pendingProfile = profile
            }
            openMenu(player, state, 3) { MenuBuilder.buildConfirmMenu(state) }
        } catch (e: Exception) {
            LOGGER.error("[BuyHead] Failed to open confirm menu for ${player.name.string} (profile: ${profile.name})", e)
            player.sendSystemMessage(Component.literal("§c[BuyHead] Failed to open confirm screen. Check server logs."))
        }
    }

    private fun openConfirmHeadMenu(player: ServerPlayer, head: dev.suwizx.buyhead.config.HeadDefinition, liveCategories: List<CategoryConfig>?) {
        try {
            val state = GuiStateManager.get(player.uuid)?.also {
                it.screen = GuiPageState.Screen.CONFIRM_PURCHASE
                it.pendingHead = head
                it.pendingProfile = null
                it.liveCategories = liveCategories
            } ?: GuiStateManager.open(player.uuid, GuiPageState.Screen.CONFIRM_PURCHASE).also {
                it.pendingHead = head
                it.liveCategories = liveCategories
            }
            openMenu(player, state, 3) { MenuBuilder.buildConfirmMenu(state) }
        } catch (e: Exception) {
            LOGGER.error("[BuyHead] Failed to open confirm menu for head '${head.name}' for ${player.name.string}", e)
            player.sendSystemMessage(Component.literal("§c[BuyHead] Failed to open confirm screen. Check server logs."))
        }
    }

    // -------------------------------------------------------------------------
    // Internal menu machinery
    // -------------------------------------------------------------------------

    private fun openMenu(player: ServerPlayer, state: GuiPageState, rows: Int, containerFactory: () -> SimpleContainer) {
        val container = containerFactory()
        val title = menuTitle(state)
        LOGGER.debug("[BuyHead] Opening {} screen for {}", state.screen, player.name.string)
        player.openMenu(object : MenuProvider {
            override fun getDisplayName(): Component = title
            override fun createMenu(syncId: Int, inv: Inventory, p: Player): AbstractContainerMenu {
                return BuyHeadMenu(syncId, inv, container, rows) { slot ->
                    try {
                        handleClick(player, slot, state, container)
                    } catch (e: Exception) {
                        LOGGER.error("[BuyHead] Unhandled exception in click handler (slot=$slot, screen=${state.screen}, player=${player.name.string})", e)
                        player.sendSystemMessage(Component.literal("§c[BuyHead] An error occurred. Check server logs."))
                    }
                }
            }
        })
    }

    private fun handleClick(player: ServerPlayer, slot: Int, state: GuiPageState, container: SimpleContainer) {
        LOGGER.debug("[BuyHead] handleClick: player={}, slot={}, screen={}, categoryId={}, page={}", 
            player.name.string, slot, state.screen, state.currentCategoryId, state.currentPage)
        when (state.screen) {
            GuiPageState.Screen.MAIN_MENU -> handleMainMenuClick(player, slot, state)
            GuiPageState.Screen.CATEGORY -> handleCategoryClick(player, slot, state)
            GuiPageState.Screen.CONFIRM_PURCHASE -> handleConfirmClick(player, slot, state)
        }
    }

    private fun handleMainMenuClick(player: ServerPlayer, slot: Int, state: GuiPageState) {
        val categories = state.liveCategories ?: BuyHeadConfig.current.categories
        LOGGER.debug("[BuyHead] Player {} clicked slot {} in Main Menu", player.name.string, slot)
        when (slot) {
            MenuBuilder.SLOT_CLOSE -> player.closeContainer()
            MenuBuilder.SLOT_PREV -> {
                if (state.currentPage > 0) {
                    state.currentPage--
                    openMainMenuSync(player, state.liveCategories)
                }
            }
            MenuBuilder.SLOT_NEXT -> {
                state.currentPage++
                openMainMenuSync(player, state.liveCategories)
            }
            in 0 until MenuBuilder.CONTENT_SLOTS -> {
                val catIndex = state.currentPage * MenuBuilder.CONTENT_SLOTS + slot
                if (catIndex < categories.size) {
                    val category = categories[catIndex]
                    LOGGER.info("[BuyHead] Player {} selected category '{}' (id={})", player.name.string, category.displayName, category.id)
                    openCategoryMenu(player, category.id)
                }
            }
        }
    }

    private fun handleCategoryClick(player: ServerPlayer, slot: Int, state: GuiPageState) {
        val categories = state.liveCategories ?: BuyHeadConfig.current.categories
        LOGGER.debug("[BuyHead] Player {} clicked slot {} in Category screen (id={})", player.name.string, slot, state.currentCategoryId)
        when (slot) {
            MenuBuilder.SLOT_BACK -> openMainMenuSync(player, state.liveCategories)
            MenuBuilder.SLOT_CLOSE -> player.closeContainer()
            MenuBuilder.SLOT_PREV -> {
                if (state.currentPage > 0) {
                    state.currentPage--
                    openCategoryMenu(player, state.currentCategoryId!!, state.currentPage)
                }
            }
            MenuBuilder.SLOT_NEXT -> {
                state.currentPage++
                openCategoryMenu(player, state.currentCategoryId!!, state.currentPage)
            }
            in 0 until MenuBuilder.CONTENT_SLOTS -> {
                val category = categories.find { it.id == state.currentCategoryId }
                if (category == null) {
                    LOGGER.warn("[BuyHead] Category '{}' not found for player {}", state.currentCategoryId, player.name.string)
                    return
                }
                val headIndex = state.currentPage * MenuBuilder.CONTENT_SLOTS + slot
                if (headIndex < category.heads.size) {
                    val head = category.heads[headIndex]
                    LOGGER.debug("[BuyHead] {} selected head '{}' at index {} in category '{}'", player.name.string, head.name, headIndex, category.id)
                    openConfirmHeadMenu(player, head, state.liveCategories)
                } else {
                    LOGGER.debug("[BuyHead] Clicked empty content slot {} (headIndex={})", slot, headIndex)
                }
            }
        }
    }

    private fun handleConfirmClick(player: ServerPlayer, slot: Int, state: GuiPageState) {
        LOGGER.debug("[BuyHead] Player {} clicked slot {} in Confirm screen (pendingHead={}, pendingProfile={})", 
            player.name.string, slot, state.pendingHead?.name, state.pendingProfile?.name)
        when (slot) {
            11, 13 -> { // 11 = Preview Head, 13 = Confirm (Lime Wool)
                val headStack = when {
                    state.pendingProfile != null -> {
                        val profile = state.pendingProfile!!
                        LOGGER.info("[BuyHead] {} confirming purchase of profile {}'s head", player.name.string, profile.name)
                        HeadItem.fromResolvedProfile(profile)
                    }
                    state.pendingHead != null -> {
                        val head = state.pendingHead!!
                        LOGGER.info("[BuyHead] {} confirming purchase of head '{}'", player.name.string, head.name)
                        HeadItem.fromTexture(head.name, head.texture)
                    }
                    else -> {
                        LOGGER.warn("[BuyHead] Confirm clicked but no pending head/profile for {}", player.name.string)
                        player.closeContainer()
                        return
                    }
                }
                val label = state.pendingProfile?.name ?: state.pendingHead?.name ?: "head"
                when (val result = HeadTransaction.execute(player, headStack)) {
                    is HeadTransaction.Result.Success -> {
                        LOGGER.info("[BuyHead] Transaction SUCCESS for {} buying '{}'", player.name.string, label)
                        player.sendSystemMessage(Component.literal("§aYou bought $label!"))
                    }
                    is HeadTransaction.Result.InsufficientFunds -> {
                        LOGGER.info("[BuyHead] Transaction FAILED: Insufficient funds for {} (has {}, needs {})", player.name.string, result.has, result.required)
                        player.sendSystemMessage(
                            Component.literal("§cNot enough ${BuyHeadConfig.current.currencyItem.substringAfterLast(':')}! Need ${result.required}, have ${result.has}.")
                        )
                    }
                    is HeadTransaction.Result.UnknownCurrency -> {
                        LOGGER.error("[BuyHead] Transaction FAILED: Unknown currency '{}'", BuyHeadConfig.current.currencyItem)
                        player.sendSystemMessage(Component.literal("§c[BuyHead] Server misconfiguration: unknown currency item. Check server logs."))
                    }
                }
                player.closeContainer()
            }
            15 -> { // Cancel — go back to the category
                LOGGER.info("[BuyHead] Player {} cancelled purchase", player.name.string)
                val catId = state.currentCategoryId
                if (catId != null) {
                    openCategoryMenu(player, catId, state.currentPage)
                } else {
                    player.closeContainer()
                }
            }
        }
    }

    private fun menuTitle(state: GuiPageState): Component = when (state.screen) {
        GuiPageState.Screen.MAIN_MENU -> Component.literal("Buy Head — Categories")
        GuiPageState.Screen.CATEGORY -> {
            val categories = state.liveCategories ?: BuyHeadConfig.current.categories
            val name = categories.find { it.id == state.currentCategoryId }?.displayName ?: "Heads"
            Component.literal("Buy Head — $name")
        }
        GuiPageState.Screen.CONFIRM_PURCHASE -> Component.literal("Confirm Purchase")
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /** Converts API response to [CategoryConfig] list for the GUI. */
    private fun toCategoryConfigs(data: Map<String, List<HeadDbClient.HeadEntry>>): List<CategoryConfig> =
        data.entries
            .filter { it.value.isNotEmpty() }
            .map { (id, heads) ->
                val displayName = id.split("-")
                    .joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
                CategoryConfig(
                    id = id,
                    displayName = displayName,
                    iconTexture = heads.first().value,
                    heads = heads.map { HeadDefinition(it.name, it.value) }
                )
            }
}
