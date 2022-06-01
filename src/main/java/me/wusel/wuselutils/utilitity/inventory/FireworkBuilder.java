/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.inventory;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkBuilder {

    private Firework firework;
    private FireworkMeta meta;

    public FireworkBuilder(Location loc, FireworkEffect.Type type, Color color, Color fadeColor, boolean flicker, boolean trail, int power) {
        this.firework = (Firework) loc.getWorld().spawn(loc, Firework.class);
        FireworkEffect effect = FireworkEffect.builder()
                .withColor(color)
                .flicker(flicker)
                .trail(trail)
                .withFade(fadeColor)
                .with(type)
                .build();
        this.meta = this.firework.getFireworkMeta();
        this.meta.addEffect(effect);
        this.meta.setPower(power);
    }

    public FireworkBuilder(Location loc, FireworkEffect.Type type, Color color, boolean flicker, boolean trail, int power) {
        this.firework = (Firework)loc.getWorld().spawn(loc, Firework.class);
        FireworkEffect effect = FireworkEffect.builder()
                .withColor(color)
                .flicker(flicker)
                .trail(trail)
                .with(type)
                .build();
        this.meta = this.firework.getFireworkMeta();
        this.meta.addEffect(effect);
        this.meta.setPower(power);
    }

    public FireworkBuilder buildItemMeta() {
        this.firework.setFireworkMeta(this.meta);
        return this;
    }

    public Firework build() {
        return this.firework;
    }
}
