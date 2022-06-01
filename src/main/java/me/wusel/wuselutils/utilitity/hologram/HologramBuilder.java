/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.hologram;

import com.google.common.collect.Lists;
import me.wusel.wuselutils.utilitity.entity.EntityBuilder;
import me.wusel.wuselutils.utilitity.FileBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HologramBuilder {

    private final String name;

    private final Location loc;

    private final ArrayList<String> lines = new ArrayList<>();

    private final HashMap<Location, Entity> entities = new HashMap<>();

    public HologramBuilder(String name, Location loc) {
        this.name = name;
        this.loc = loc;
    }

    public HologramBuilder(String name, World world, double x, double y, double z) {
        Location loc = new Location(world, x, y, z);
        this.name = name;
        this.loc = loc;
    }

    public HologramBuilder(String name, String world, double x, double y, double z) {
        Location loc = new Location(Bukkit.getWorld(world), x, y, z);
        this.name = name;
        this.loc = loc;
    }

    public HologramBuilder setLine(int line, String text) {
        this.lines.set(line, text);
        return this;
    }

    public HologramBuilder addLines(List<String> lines) {
        for (String line : lines)
            this.lines.add(line);
        return this;
    }

    public HologramBuilder addLine(String text) {
        this.lines.add(text);
        return this;
    }

    public HologramBuilder removeLine(String line) {
        this.lines.remove(line);
        return this;
    }

    public HologramBuilder spawn() {
        Location loc = this.loc;
        for (String line : Lists.reverse(this.lines)) {
            ArmorStand entity = (ArmorStand)(new EntityBuilder(EntityType.ARMOR_STAND, this.loc)).setCustomName(line).setCustomNameVisible(true).spawn();
            entity.setVisible(false);
            entity.setGravity(false);
            entity.setBasePlate(false);
            this.entities.put(loc, entity);
            loc = loc.add(0.0D, 0.25D, 0.0D);
        }
        return this;
    }

    public void remove(Location loc) {
        if (this.entities.containsKey(loc)) {
            ((Entity)this.entities.get(loc)).remove();
            this.entities.remove(loc);
        }
    }

    public void saveInFile(String path, String file) {
        FileBuilder fb = new FileBuilder(path, file);
        YamlConfiguration yml = (new FileBuilder(path, file)).getConfiguration();
        yml.set(String.valueOf(this.name) + ".World", this.loc.getWorld().getName());
        yml.set(String.valueOf(this.name) + ".X", Double.valueOf(this.loc.getX()));
        yml.set(String.valueOf(this.name) + ".Y", Double.valueOf(this.loc.getY()));
        yml.set(String.valueOf(this.name) + ".Z", Double.valueOf(this.loc.getZ()));
        yml.set(String.valueOf(this.name) + ".Lines", this.lines);
        fb.save();
    }

    public void removeFromFile(String path, String file) {
        FileBuilder fb = new FileBuilder(path, file);
        YamlConfiguration yml = (new FileBuilder(path, file)).getConfiguration();
        yml.set(String.valueOf(this.name) + ".World", null);
        yml.set(String.valueOf(this.name) + ".X", null);
        yml.set(String.valueOf(this.name) + ".Y", null);
        yml.set(String.valueOf(this.name) + ".Z", null);
        yml.set(String.valueOf(this.name) + ".Lines", null);
        fb.save();
    }
}
