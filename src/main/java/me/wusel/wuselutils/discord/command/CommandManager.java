/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.discord.command;

import me.wusel.wuselutils.discord.DiscordBot;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    private ConcurrentHashMap<String, Command> commands;

    private DiscordBot discordBot;

    public CommandManager(DiscordBot discordBot) {
        this.commands = new ConcurrentHashMap<>();
        this.discordBot = discordBot;
    }

    public boolean perform(String command, User user, TextChannel channel, Message message) {
        String[] args = message.getContent().substring(discordBot.getPrefix().length()).split(" ");

        Command cmd;
        if ((cmd = this.commands.get(command.toLowerCase())) != null) {
            try {
                cmd.perform(user, channel, message, args);
            }catch (Exception e) {
                System.out.println("Error performing: " + message.getContent());
                return false;
            }
            System.out.println("Performing: " + message.getContent());
            return true;
        }
        System.out.println("Command does not exist (" + message.getContent() + ")");
        return false;
    }

    public void registerCommand(String command, Command obj) {
        this.commands.put(command, obj);
    }
}
