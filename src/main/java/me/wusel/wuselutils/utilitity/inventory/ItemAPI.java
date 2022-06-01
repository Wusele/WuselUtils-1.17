/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.inventory;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemAPI {

    private ItemStack item;
    private ItemMeta meta;

    public ItemAPI(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public ItemAPI(ItemStack itemStack) {
        this.item = itemStack;
        this.meta = itemStack.getItemMeta();
    }

    public ItemAPI setDisplayName(String displayName) {
        this.meta.setDisplayName(displayName);
        return this;
    }

    public ItemAPI setLore(String... lore) {
        this.meta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemAPI setLocalizedName(String name) {
        this.meta.setLocalizedName(name);
        return this;
    }

    public ItemAPI setUnbreakable(Boolean unbreakable) {
        this.meta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemAPI addEnchant(Enchantment ench, int level, boolean ignoreLevelRestriction) {
        this.meta.addEnchant(ench, level, ignoreLevelRestriction);
        return this;
    }

    public ItemAPI addItemFlags(ItemFlag... itemFlags) {
        this.meta.addItemFlags(itemFlags);
        return this;
    }

    public ItemAPI addAttributeModifier(Attribute attribute, AttributeModifier modifier) {
        this.meta.addAttributeModifier(attribute, modifier);
        return this;
    }

    public ItemStack finish() {
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}
