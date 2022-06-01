/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.utilitity.titles;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class BossbarBuilder {
    private int bartime;

    private BossBar bar;

    private Player player;

    private Plugin plugin;

    public BossbarBuilder(Plugin plugin, Player p, String title, BarColor barcolor, BarStyle barstyle) {
        this.bar = Bukkit.createBossBar(title, barcolor, barstyle, new BarFlag[0]);
        this.player = p;
        this.plugin = plugin;
    }

    public BossbarBuilder addFlag(BarFlag flag) {
        this.bar.addFlag(flag);
        return this;
    }

    public BossbarBuilder setTime(int time) {
        this.bartime = time;
        return this;
    }

    public void build() {
        this.bar.addPlayer(this.player);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> this.bar.removePlayer(this.player),

                this.bartime);
    }
}
