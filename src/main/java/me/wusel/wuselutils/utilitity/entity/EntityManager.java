/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class EntityManager {
    public static void killEntity(int id, Location loc) {
        ((Entity) loc.getWorld().getEntities().get(id)).remove();
    }

    public static int getIdByName(String name, Location loc) {
        for (Entity e : loc.getWorld().getEntities()) {
            if (e.getCustomName().equalsIgnoreCase(name))
                return e.getEntityId();
        }
        return -1;
    }
}
