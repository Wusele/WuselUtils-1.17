/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.items;

import java.util.Random;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherArmorBuilder {
    private LeatherArmorMeta lm;

    private ItemStack item;

    public LeatherArmorBuilder(ItemStack armor) {
        this.item = armor;
        this.lm = (LeatherArmorMeta)this.item.getItemMeta();
    }

    public LeatherArmorBuilder setColor(Color color) {
        this.lm.setColor(color);
        return this;
    }

    public LeatherArmorBuilder setRandomColor() {
        this.lm.setColor(Color.fromRGB(randomColor(255) + 1, randomColor(255) + 1, randomColor(255) + 1));
        return this;
    }

    private int randomColor(int max) {
        Random r = new Random();
        return r.nextInt(max);
    }

    public LeatherArmorBuilder buildItemMeta() {
        this.item.setItemMeta((ItemMeta)this.lm);
        return this;
    }

    public ItemStack build() {
        return this.item;
    }
}
