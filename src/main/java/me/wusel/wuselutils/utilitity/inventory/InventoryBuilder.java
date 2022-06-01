/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class InventoryBuilder {

    private Inventory inv;

    private InventoryHolder holder;
    private InventoryType type;
    private int size;
    private String title;

    private HashMap<Integer, ItemStack> items;

    public InventoryBuilder() {

        items = new HashMap<>();

    }


    public InventoryBuilder setHolder(InventoryHolder holder) {
        this.holder = holder;
        return this;
    }

    public InventoryBuilder setType(InventoryType type) {
        this.type = type;
        return this;
    }

    public InventoryBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public InventoryBuilder setTitle(String title) {
        this.title = title;
        return this;
    }



    public InventoryBuilder setItem(int index, ItemStack itemStack) {
        inv.setItem(index, itemStack);
        return this;
    }



    private void setItems() {
        items.forEach(new BiConsumer<Integer, ItemStack>() {
            @Override
            public void accept(Integer index, ItemStack itemStack) {
                inv.setItem(index, itemStack);
            }
        });
    }

    public Inventory complete() {

        if (type == null) {
            inv = Bukkit.createInventory(holder, size, title);
            setItems();
            return inv;
        }
        inv = Bukkit.createInventory(holder, type, title);
        setItems();
        return inv;
    }

}
