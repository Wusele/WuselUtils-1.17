/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */
package me.wusel.wuselutils.utilitity.items;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.UUID;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullBuilder {
    private SkullMeta sm;

    private ItemStack item;

    public SkullBuilder(ItemStack skull) {
        this.item = skull;
        this.sm = (SkullMeta)skull.getItemMeta();
    }

    public SkullBuilder setOwnerByName(String owner) {
        this.sm.setOwner(owner);
        return this;
    }

    public SkullBuilder setTexture(String texture) {
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", texture));
        Field profileField = null;
        try {
            profileField = this.sm.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(this.sm, profile);
        } catch (IllegalArgumentException|IllegalAccessException|NoSuchFieldException|SecurityException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SkullBuilder buildItemMeta() {
        this.item.setItemMeta((ItemMeta)this.sm);
        return this;
    }

    public ItemStack build() {
        return this.item;
    }
}

