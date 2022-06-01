/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.items;

import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class BookBuilder {

    private BookMeta bm;

    private ItemStack item;

    public BookBuilder(ItemStack writtenbook) {
        this.item = writtenbook;
        this.bm = (BookMeta)this.item.getItemMeta();
    }

    public BookBuilder setAuthor(String name) {
        this.bm.setAuthor(name);
        return this;
    }

    public BookBuilder addPage(String... content) {
        this.bm.addPage(content);
        return this;
    }

    public BookBuilder addPages(List<String> contents) {
        for (String content : contents) {
            this.bm.addPage(new String[] { content });
        }
        return this;
    }

    public int getPageCount() {
        return this.bm.getPageCount();
    }

    public BookBuilder buildItemMeta() {
        this.item.setItemMeta((ItemMeta)this.bm);
        return this;
    }

    public ItemStack build() {
        return this.item;
    }
}
