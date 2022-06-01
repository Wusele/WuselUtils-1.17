/*
 * Copyright (c) 2022.
 * Class created by Wusel
 */

package me.wusel.wuselutils.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnloadCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("wuselutils.unload")) {
            if (strings.length == 1) {
                String plugin = strings[0];
                if (Bukkit.getPluginManager().getPlugin(plugin) == null) {
                    commandSender.sendMessage("§cThis plugin is not installed please check your spelling. This command is case sensitive!");
                    return true;
                }
                Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(plugin));
                commandSender.sendMessage("§7Successfully unloaded §e§l" + plugin);
                return true;
            }
            commandSender.sendMessage("§cUsage: /unload {plugin}");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArrayList<String> plugins = new ArrayList<>();
        List<String> tabComplete = new ArrayList<>();
        for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
            if (p.isEnabled())
                plugins.add(p.getName());
        }
        if (strings.length == 1) {
            StringUtil.copyPartialMatches(strings[0], plugins, tabComplete);
        }
        return tabComplete;
    }
}
