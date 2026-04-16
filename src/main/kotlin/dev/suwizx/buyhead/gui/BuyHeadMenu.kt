package dev.suwizx.buyhead.gui

import net.minecraft.world.Container
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.inventory.ContainerInput
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.ItemStack

class BuyHeadMenu(
    containerId: Int,
    playerInventory: Inventory,
    container: Container,
    rows: Int,
    private val onSlotClick: (slot: Int) -> Unit
) : ChestMenu(rowsToMenuType(rows), containerId, playerInventory, container, rows) {

    override fun clicked(slotId: Int, button: Int, containerInput: ContainerInput, player: Player) {
        // Block ALL inventory manipulation — only fire our callback for valid chest slots
        if (slotId in 0 until (getRowCount() * 9)) {
            onSlotClick(slotId)
        }
        
        // In 1.21.2+, forcing a sync helps prevent client-side mods from crashing 
        // when they expect an item to move but it doesn't.
        if (player is net.minecraft.server.level.ServerPlayer) {
            this.sendAllDataToRemote()
        }
        // Never call super — prevents any item movement
    }

    override fun quickMoveStack(player: Player, index: Int): ItemStack = ItemStack.EMPTY

    override fun stillValid(player: Player): Boolean = true

    companion object {
        fun rowsToMenuType(rows: Int): MenuType<ChestMenu> = when (rows) {
            1 -> MenuType.GENERIC_9x1
            2 -> MenuType.GENERIC_9x2
            3 -> MenuType.GENERIC_9x3
            4 -> MenuType.GENERIC_9x4
            5 -> MenuType.GENERIC_9x5
            else -> MenuType.GENERIC_9x6
        }
    }
}
