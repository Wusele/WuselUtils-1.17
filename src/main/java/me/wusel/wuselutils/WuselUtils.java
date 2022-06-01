/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils;

import me.wusel.wuselutils.command.AddonCommand;
import me.wusel.wuselutils.command.UnloadCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class WuselUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("WuselUtils has started!");


        getCommand("addons").setExecutor(new AddonCommand());
        getCommand("addons").setTabCompleter(new AddonCommand());

        getCommand("unload").setExecutor(new UnloadCommand());
        getCommand("unload").setTabCompleter(new UnloadCommand());

        AddonCommand.setupAddons();

    }

    @Override
    public void onDisable() {
        getLogger().info("WuselUtils has stopped!");
    }

}
