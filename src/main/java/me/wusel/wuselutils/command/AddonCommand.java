/*
 * Copyright (c) 2022.
 * Class created by Wusel
 */

package me.wusel.wuselutils.command;

import me.wusel.wuselutils.utilitity.Downloader;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AddonCommand implements CommandExecutor, TabCompleter {

    private static ArrayList<String> addons = new ArrayList<>();
    private static ArrayList<String> availableAddons = new ArrayList<>();

    public static void setupAddons() {
        availableAddons.add("WuselCommands");
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (availableAddons.contains(plugin.getName())) {
                availableAddons.remove(plugin.getName());
                addons.add(plugin.getName());
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("wuselutils.addons.install")) {
            return false;
        }
        // /addons <list|add|remove|available>
        if (strings.length == 1) {
            switch (strings[0]) {
                case "list":
                    if (addons.size() == 0) {
                        commandSender.sendMessage("§c§lYou have no Addons installed");
                        break;
                    }
                    commandSender.sendMessage("§7List of §a§linstalled§r§7 Addons");
                    for (String addon : addons) {
                        commandSender.sendMessage("§7- §e§l" + addon);
                    }
                    break;
                case "available":
                    if (availableAddons.size() == 0) {
                        commandSender.sendMessage("§c§lYou have no available Addons");
                        break;
                    }
                    commandSender.sendMessage("§7List of §a§lavailable§r§7 Addons");
                    for (String addon : availableAddons) {
                        commandSender.sendMessage("§7- §e§l" + addon);
                    }
                    break;
                case "add":
                case "remove":
                    commandSender.sendMessage("§cUsage: /Addons <add|remove> {addon}");
                    break;
                default:
                    commandSender.sendMessage("§cUsage: /Addons <add|remove|list|available>");
                    break;
            }
            return true;
        }

        if (strings.length == 2) {
            String addon = strings[1];
            switch (strings[0]) {
                case "add":
                    if (addons.contains(addon)) {
                        commandSender.sendMessage("§cThis plugin is §lalready §r§cinstalled");
                        break;
                    }
                    if (!availableAddons.contains(addon)) {
                        commandSender.sendMessage("§cThis plugin is §lnot §r§cavailable");
                        break;
                    }
                    switch (addon.toLowerCase()) {
                        case "wuselcommands":
                            commandSender.sendMessage("§7Installing §a§lWuselCommands...");
                            //Downloader.download("https://www.spigotmc.org/resources/wuselcommands.101410/download?version=449045", addon.toLowerCase() + ".jar");
                            Downloader.downloadPlugin("101410", addon + ".jar");
                            commandSender.sendMessage("§7Successfully installed §a§lWuselCommands");
                            commandSender.sendMessage("§7To activate the addon you need to §a§lreload§r§7 the server using: §e§l/reload§r§7 or §e§l/stop");
                            addons.add(addon.toLowerCase());
                            availableAddons.remove(addon.toLowerCase());
                            break;
                        default:
                            commandSender.sendMessage("§cThis addon doesn't seem to exists..");
                            break;
                    }
                    break;
                case "remove":
                    if (!addons.contains(addon)) {
                        commandSender.sendMessage("§cThis addons is §lnot §r§cinstalled");
                        break;
                    }
                    File file = new File("plugins/" + addon + ".jar");

                    Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(addon));

                    file.delete();

                    commandSender.sendMessage("§7Successfully uninstalled §a§l" + addon);

                    break;
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        // /addons add|remove {plugin}
        List<String> tabComplete = new ArrayList<>();
        if (strings.length == 2) {
            if (strings[0].equalsIgnoreCase("add")) {
                StringUtil.copyPartialMatches(strings[1], availableAddons, tabComplete);
            }
            if (strings[0].equalsIgnoreCase("remove")) {
                StringUtil.copyPartialMatches(strings[1], addons, tabComplete);
            }
        }
        if (strings.length == 1) {
            StringUtil.copyPartialMatches(strings[0], Arrays.asList("add", "remove", "list", "available"), tabComplete);
        }
        return tabComplete;
    }
}
