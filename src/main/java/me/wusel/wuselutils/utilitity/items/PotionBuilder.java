/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.items;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionBuilder {
    private PotionMeta pm;

    private ItemStack item;

    public PotionBuilder(ItemStack potion) {
        this.item = potion;
        this.pm = (PotionMeta)this.item.getItemMeta();
    }

    public PotionBuilder setColor(Color color) {
        this.pm.setColor(color);
        return this;
    }

    public PotionBuilder addEffect(PotionEffectType type, int dura, int amp) {
        this.pm.addCustomEffect(new PotionEffect(type, dura, amp), true);
        return this;
    }

    public PotionBuilder buildItemMeta() {
        this.item.setItemMeta((ItemMeta)this.pm);
        return this;
    }

    public ItemStack build() {
        return this.item;
    }
}
