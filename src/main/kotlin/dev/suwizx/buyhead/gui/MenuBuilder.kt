package dev.suwizx.buyhead.gui

import dev.suwizx.buyhead.config.BuyHeadConfig
import dev.suwizx.buyhead.config.CategoryConfig
import dev.suwizx.buyhead.head.HeadItem
import net.minecraft.ChatFormatting
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.SimpleContainer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.component.ItemLore

object MenuBuilder {

    // Navigation slot indices (bottom row of 6-row chest)
    const val SLOT_BACK = 45
    const val SLOT_PREV = 46
    const val SLOT_INFO = 48
    const val SLOT_NEXT = 50
    const val SLOT_CLOSE = 53
    const val CONTENT_SLOTS = 45  // rows 1–5

    fun buildMainMenu(state: GuiPageState): SimpleContainer {
        val container = SimpleContainer(54)
        val categories = state.liveCategories ?: BuyHeadConfig.current.categories
        val totalPages = pageCount(categories.size)
        val page = state.currentPage.coerceIn(0, (totalPages - 1).coerceAtLeast(0))
        state.currentPage = page

        val start = page * CONTENT_SLOTS
        val pageItems = categories.drop(start).take(CONTENT_SLOTS)

        pageItems.forEachIndexed { i, cat ->
            container.setItem(i, categoryIcon(cat))
        }

        // Filler for remaining content slots
        for (i in pageItems.size until CONTENT_SLOTS) {
            container.setItem(i, HeadItem.filler())
        }

        fillNavBar(container, page, totalPages)
        return container
    }

    fun buildCategoryMenu(state: GuiPageState): SimpleContainer {
        val container = SimpleContainer(54)
        val categories = state.liveCategories ?: BuyHeadConfig.current.categories
        val category = categories.find { it.id == state.currentCategoryId }
        
        if (category == null) {
            val logger = org.slf4j.LoggerFactory.getLogger("buy-head")
            logger.warn("[BuyHead] buildCategoryMenu: Category '{}' not found in current session", state.currentCategoryId)
            fillNavBar(container, 0, 1, showBack = true)
            return container
        }

        val totalPages = pageCount(category.heads.size)
        val page = state.currentPage.coerceIn(0, (totalPages - 1).coerceAtLeast(0))
        state.currentPage = page

        val cost = BuyHeadConfig.current.currencyAmount
        val currency = BuyHeadConfig.current.currencyItem.substringAfterLast(':')
            .replace('_', ' ')
            .replaceFirstChar { it.uppercase() }
        val lore = listOf(
            Component.literal("Cost: $cost $currency").withStyle(ChatFormatting.YELLOW)
        )

        val start = page * CONTENT_SLOTS
        val displayedHeads = category.heads.drop(start).take(CONTENT_SLOTS)
        displayedHeads.forEachIndexed { i, head ->
            container.setItem(i, HeadItem.fromTexture(head.name, head.texture, lore))
        }

        // Filler for any remaining content slots
        for (i in displayedHeads.size until CONTENT_SLOTS) {
            container.setItem(i, HeadItem.filler())
        }

        fillNavBar(container, page, totalPages, showBack = true)
        return container
    }

    fun buildConfirmMenu(state: GuiPageState): SimpleContainer {
        val container = SimpleContainer(27)

        val cost = BuyHeadConfig.current.currencyAmount
        val currency = BuyHeadConfig.current.currencyItem.substringAfterLast(':')
            .replace('_', ' ')
            .replaceFirstChar { it.uppercase() }

        // Preview head — either from a resolved GameProfile or a baked texture
        val previewItem = when {
            state.pendingProfile != null -> {
                val profile = state.pendingProfile!!
                val lore = listOf(
                    Component.literal("Player: ${profile.name}").withStyle(ChatFormatting.GRAY),
                    Component.literal("Cost: $cost $currency").withStyle(ChatFormatting.YELLOW)
                )
                HeadItem.fromResolvedProfile(profile, lore)
            }
            state.pendingHead != null -> {
                val head = state.pendingHead!!
                val lore = listOf(
                    Component.literal("Cost: $cost $currency").withStyle(ChatFormatting.YELLOW)
                )
                HeadItem.fromTexture(head.name, head.texture, lore)
            }
            else -> {
                return container
            }
        }
        container.setItem(11, previewItem)

        // Confirm button
        val confirm = ItemStack(Items.LIME_WOOL)
        confirm.set(DataComponents.ITEM_NAME, Component.literal("Confirm Purchase").withStyle(ChatFormatting.GREEN))
        confirm.set(DataComponents.LORE, ItemLore(listOf(
            Component.literal("Click to buy for $cost $currency").withStyle(ChatFormatting.GRAY)
        )))
        container.setItem(13, confirm)

        // Cancel button
        val cancel = ItemStack(Items.RED_WOOL)
        cancel.set(DataComponents.ITEM_NAME, Component.literal("Cancel").withStyle(ChatFormatting.RED))
        container.setItem(15, cancel)

        // Filler
        for (i in 0 until 27) {
            if (container.getItem(i).isEmpty) container.setItem(i, HeadItem.filler())
        }
        return container
    }

    private fun categoryIcon(cat: CategoryConfig): ItemStack {
        val lore = listOf(
            Component.literal("${cat.heads.size} heads available").withStyle(ChatFormatting.GRAY),
            Component.literal("Click to browse").withStyle(ChatFormatting.YELLOW)
        )
        return HeadItem.fromTexture(cat.displayName, cat.iconTexture, lore)
    }

    private fun fillNavBar(container: SimpleContainer, page: Int, totalPages: Int, showBack: Boolean = false) {
        // Filler for entire bottom row first
        for (i in CONTENT_SLOTS until 54) {
            container.setItem(i, HeadItem.filler())
        }

        if (showBack) {
            val back = ItemStack(Items.ARROW)
            back.set(DataComponents.ITEM_NAME, Component.literal("Back").withStyle(ChatFormatting.WHITE))
            container.setItem(SLOT_BACK, back)
        }

        if (page > 0) {
            val prev = ItemStack(Items.ARROW)
            prev.set(DataComponents.ITEM_NAME, Component.literal("Previous Page").withStyle(ChatFormatting.WHITE))
            container.setItem(SLOT_PREV, prev)
        }

        val info = ItemStack(Items.BOOK)
        info.set(DataComponents.ITEM_NAME, Component.literal("Page ${page + 1} / $totalPages").withStyle(ChatFormatting.WHITE))
        container.setItem(SLOT_INFO, info)

        if (page < totalPages - 1) {
            val next = ItemStack(Items.ARROW)
            next.set(DataComponents.ITEM_NAME, Component.literal("Next Page").withStyle(ChatFormatting.WHITE))
            container.setItem(SLOT_NEXT, next)
        }

        val close = ItemStack(Items.BARRIER)
        close.set(DataComponents.ITEM_NAME, Component.literal("Close").withStyle(ChatFormatting.RED))
        container.setItem(SLOT_CLOSE, close)
    }

    private fun pageCount(itemCount: Int): Int = ((itemCount - 1) / CONTENT_SLOTS + 1).coerceAtLeast(1)
}
