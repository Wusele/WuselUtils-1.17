/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.entity;

import org.bukkit.Location;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class EntityBuilder {

    private Entity entity;

    public EntityBuilder(EntityType type, Location loc) {
        Entity e = loc.getWorld().spawnEntity(loc, type);
        this.entity = e;
    }

    public EntityBuilder setCustomName(String name) {
        this.entity.setCustomName(name);
        setCustomNameVisible(true);
        return this;
    }

    public EntityBuilder setCustomNameVisible(Boolean visible) {
        this.entity.setCustomNameVisible(visible);
        return this;
    }

    public EntityBuilder setPassenger(Entity entity) {
        this.entity.setPassenger(entity);
        return this;
    }


    public EntityBuilder setAge(EntityAge age) {
        if (this.entity instanceof Ageable) {
            switch (age) {
                case BABY:
                    ((Ageable)this.entity).setBaby();
                    break;
                default:
                    ((Ageable)this.entity).setAdult();
                    break;
            }
        } else {
            throw new IllegalArgumentException("Entity not ageable.");
        }
        return this;
    }


    public enum EntityAge {
        BABY, ADULT;
    }

    public Entity spawn() {
        return this.entity;
    }


}
