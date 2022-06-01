package me.wusel.wuselutils.utilitity.role;

import org.bukkit.ChatColor;

public class Role {

    private String prefix, suffix, name;
    private ChatColor color;

    public Role(String prefix, String suffix, String name, ChatColor color) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.name = name;
        this.color = color;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }
}
