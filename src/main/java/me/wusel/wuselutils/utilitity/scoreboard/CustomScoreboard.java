package me.wusel.wuselutils.utilitity.scoreboard;

import me.wusel.wuselutils.utilitity.role.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class CustomScoreboard {
    private final Scoreboard scoreboard;
    private final Objective sidebarObjective;

    public CustomScoreboard(String displayname) {
        this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

        if (scoreboard.getObjective("sidebar") != null)
            scoreboard.getObjective("sidebar").unregister();

        sidebarObjective = scoreboard.registerNewObjective("sidebar", "dummy", displayname);
        sidebarObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void setSidebarScore(Integer slot, String content) {
        if (slot < 0) throw new IllegalArgumentException("slot must be greater than 0");
        if (slot > 16) throw new IllegalArgumentException("slot must be lower than 16");

        Team team = getOrCreateTeam("sidebar" + slot);
        String entry = getEntry(slot);

        if (content == null) {
            scoreboard.resetScores(entry);
            return;
        }

        team.setPrefix(content);
        team.addEntry(entry);
        sidebarObjective.getScore(entry).setScore(slot);
    }

    public void addPlayer(Player player, String teamName, String prefix, String suffix, ChatColor color) {
        Team team = getOrCreateTeam(teamName);
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.setColor(color);
        team.addEntry(player.getName());
    }

    public void addPlayer(Player player, Role role) {
        Team team = getOrCreateTeam(role.getName());
        team.setPrefix(role.getPrefix());
        team.setSuffix(role.getSuffix());
        team.setColor(role.getColor());
        team.addEntry(player.getName());
    }

    private Team getOrCreateTeam(String name) {
        Team team = scoreboard.getTeam(name);
        if (team != null) return team;
        return scoreboard.registerNewTeam(name);
    }

    private String getEntry(Integer slot) {
        return ChatColor.values()[slot].toString() + ChatColor.values()[slot+1];
    }
}
