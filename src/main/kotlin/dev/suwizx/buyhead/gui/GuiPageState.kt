package dev.suwizx.buyhead.gui

import com.mojang.authlib.GameProfile
import dev.suwizx.buyhead.config.CategoryConfig
import dev.suwizx.buyhead.config.HeadDefinition
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

data class GuiPageState(
    val playerId: UUID,
    var screen: Screen,
    var currentCategoryId: String? = null,
    var currentPage: Int = 0,
    var pendingProfile: GameProfile? = null,         // confirm-purchase: /buyhead player <name>
    var pendingHead: HeadDefinition? = null,          // confirm-purchase: category suggestion head
    var liveCategories: List<CategoryConfig>? = null  // HeadDB live data; null = fall back to config
) {
    enum class Screen {
        MAIN_MENU,
        CATEGORY,
        CONFIRM_PURCHASE
    }
}

object GuiStateManager {
    private val states = ConcurrentHashMap<UUID, GuiPageState>()

    fun open(playerId: UUID, screen: GuiPageState.Screen): GuiPageState {
        val state = GuiPageState(playerId, screen)
        states[playerId] = state
        return state
    }

    fun get(playerId: UUID): GuiPageState? = states[playerId]

    fun remove(playerId: UUID) {
        states.remove(playerId)
    }
}
