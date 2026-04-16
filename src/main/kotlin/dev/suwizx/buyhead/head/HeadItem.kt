package dev.suwizx.buyhead.head

import com.google.common.collect.LinkedListMultimap
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import com.mojang.authlib.properties.PropertyMap
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.component.ItemLore
import net.minecraft.world.item.component.ResolvableProfile
import java.util.UUID

object HeadItem {

    /**
     * Creates a player head ItemStack from a pre-baked base64 skin texture value.
     * Use this for preset heads stored in the config.
     */
    fun fromTexture(displayName: String, textureValue: String, loreLines: List<Component> = emptyList()): ItemStack {
        // We include BOTH name and texture in the UUID generation for uniqueness.
        val uuid = UUID.nameUUIDFromBytes("OfflinePlayer:$displayName:$textureValue".toByteArray(Charsets.UTF_8))
        
        // We use a fixed short name for the internal GameProfile. 
        // This avoids "String too big" or "Invalid character" errors in the network layer.
        // The player still sees the correct name via the ITEM_NAME component.
        val profileName = "Head"
        
        val backing = LinkedListMultimap.create<String, Property>()
        backing.put("textures", Property("textures", textureValue))
        val profile = GameProfile(uuid, profileName, PropertyMap(backing))

        val stack = ItemStack(Items.PLAYER_HEAD)
        stack.set(DataComponents.PROFILE, ResolvableProfile.createResolved(profile))
        stack.set(DataComponents.ITEM_NAME, Component.literal(displayName))
        if (loreLines.isNotEmpty()) {
            stack.set(DataComponents.LORE, ItemLore(loreLines))
        }
        return stack
    }

    /**
     * Creates a player head from a fully resolved GameProfile (has textures property populated).
     * Use this for the /buyhead player <username> flow after async Mojang API resolution.
     */
    fun fromResolvedProfile(profile: GameProfile, loreLines: List<Component> = emptyList()): ItemStack {
        val stack = ItemStack(Items.PLAYER_HEAD)
        stack.set(DataComponents.PROFILE, ResolvableProfile.createResolved(profile))
        stack.set(DataComponents.ITEM_NAME, Component.literal("${profile.name}'s Head"))
        if (loreLines.isNotEmpty()) {
            stack.set(DataComponents.LORE, ItemLore(loreLines))
        }
        return stack
    }

    /** Creates a simple decorative filler item (glass pane) for GUI borders. */
    fun filler(): ItemStack {
        return ItemStack(Items.GRAY_STAINED_GLASS_PANE)
    }
}
