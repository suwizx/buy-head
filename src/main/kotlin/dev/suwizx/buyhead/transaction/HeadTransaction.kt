package dev.suwizx.buyhead.transaction

import dev.suwizx.buyhead.config.BuyHeadConfig
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.SimpleContainer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

object HeadTransaction {

    sealed class Result {
        object Success : Result()
        object UnknownCurrency : Result()
        data class InsufficientFunds(val required: Int, val has: Int) : Result()
    }

    fun execute(player: ServerPlayer, headStack: ItemStack): Result {
        val config = BuyHeadConfig.current
        val currencyId = Identifier.tryParse(config.currencyItem)
        
        if (currencyId == null) {
            org.slf4j.LoggerFactory.getLogger("buy-head").error("[BuyHead] Failed to parse currency ID: {}", config.currencyItem)
            return Result.UnknownCurrency
        }
        
        val currencyItem = BuiltInRegistries.ITEM.getValue(currencyId)
        if (currencyItem == Items.AIR) {
            org.slf4j.LoggerFactory.getLogger("buy-head").error("[BuyHead] Currency item is AIR (not found): {}", config.currencyItem)
            return Result.UnknownCurrency
        }

        val inventory = player.inventory
        // Predicate matching the currency item
        val predicate: (ItemStack) -> Boolean = { stack -> stack.getItem() == currencyItem }
        // Empty container so only the player's inventory is searched
        val emptyContainer = SimpleContainer(0)

        val has = inventory.clearOrCountMatchingItems(predicate, 0, emptyContainer)
        org.slf4j.LoggerFactory.getLogger("buy-head").info("[BuyHead] Player {} has {}/{} of {}", 
            player.name.string, has, config.currencyAmount, config.currencyItem)
            
        if (has < config.currencyAmount) {
            return Result.InsufficientFunds(required = config.currencyAmount, has = has)
        }

        val cleared = inventory.clearOrCountMatchingItems(predicate, config.currencyAmount, emptyContainer)
        org.slf4j.LoggerFactory.getLogger("buy-head").info("[BuyHead] Deducted {} {} from {}", cleared, config.currencyItem, player.name.string)

        if (!inventory.add(headStack.copy())) {
            org.slf4j.LoggerFactory.getLogger("buy-head").info("[BuyHead] Inventory full for {}, dropping head on ground", player.name.string)
            player.drop(headStack.copy(), false)
        }

        player.closeContainer()
        return Result.Success
    }
}
