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

public class ArrowBuilder {
    private ItemStack item;

    private PotionMeta pm;

    public ArrowBuilder(ItemStack arrow) {
        this.item = arrow;
        this.pm = (PotionMeta)arrow.getItemMeta();
    }

    public ArrowBuilder setColor(Color color) {
        this.pm.setColor(color);
        return this;
    }

    public ArrowBuilder addCustomEffect(PotionEffectType type, int dura, int amp) {
        this.pm.addCustomEffect(new PotionEffect(type, dura, amp), true);
        return this;
    }

    public ArrowBuilder buildItemMeta() {
        this.item.setItemMeta((ItemMeta)this.pm);
        return this;
    }

    public ItemStack build() {
        return this.item;
    }
}
