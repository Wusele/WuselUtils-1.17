/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.items;

import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class BannerBuilder {
    private BannerMeta bm;

    private ItemStack item;

    public BannerBuilder(ItemStack banner) {
        this.item = banner;
        this.bm = (BannerMeta)this.item.getItemMeta();
    }

    public BannerBuilder setBasecolor(DyeColor color) {
        this.bm.setBaseColor(color);
        return this;
    }

    public BannerBuilder addPattern(DyeColor color, PatternType pattern) {
        this.bm.addPattern(new Pattern(color, pattern));
        return this;
    }

    public BannerBuilder buildItemMeta() {
        this.item.setItemMeta((ItemMeta)this.bm);
        return this;
    }

    public ItemStack build() {
        return this.item;
    }
}
