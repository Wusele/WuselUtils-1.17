package me.wusel.wuselutils.utilitity.timer;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Timer {


    private BukkitTask runnable;

    private boolean paused;
    private int elapsedTime;


    public void start(Plugin plugin, long delay, long period, TimerData timerData) {
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if (!paused)
                    elapsedTime += 1;
                int seconds = (elapsedTime % 60);
                int minutes = (elapsedTime / 60) % 60;
                int hours = (elapsedTime / 3600);

                timerData.run(seconds, minutes, hours, paused);
            }
        }.runTaskTimer(plugin, delay, period);
    }

    public void stop()  {
        runnable.cancel();
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public int getHours() {
        return (elapsedTime / 3600);
    }

    public int getMinutes() {
        return (elapsedTime / 60) % 60;
    }

    public int getSeconds() {
        return (elapsedTime % 60);
    }

    public void sendActionbar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }
}
