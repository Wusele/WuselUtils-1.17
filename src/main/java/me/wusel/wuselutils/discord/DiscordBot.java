package me.wusel.wuselutils.discord;

import me.wusel.wuselutils.discord.audio.AudioManager;
import me.wusel.wuselutils.discord.command.CommandManager;
import org.bukkit.plugin.Plugin;
import org.javacord.api.AccountType;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;


public class DiscordBot
{

    /**
     * Creates an instance of this class to run the onConnect method in Runnable
     */
    private DiscordBot instance;
    private CommandManager commandManager;
    private AudioManager audioManager;

    private Runnable onConnect;

    private String prefix = "";

    /**
     * Creates an instance of the DiscordApi class
     */
    private DiscordApi api;

    /**
     * Saves instances of the token and the plugin that corresponds to this bot
     */
    private final String token;
    private final AccountType accountType;

    private Plugin plugin;

    /**
     * Constructor to initialize params
     * @param plugin
     * @param token
     */
    public DiscordBot(Plugin plugin, String token, AccountType accountType) {
        this.token = token;
        this.plugin = plugin;
        this.instance = this;
        this.accountType = accountType;
        commandManager = new CommandManager(this);
    }

    /**
     * Creates a bot, sets the token, logs in, runs onConnect Method and prints out a log in case of an error.
     * Uses a new Thread
     */
    public void start()
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                new DiscordApiBuilder()
                        .setToken(token)
                        .setAccountType(accountType)
                        .login()
                        .thenAccept(getInstance()::onConnectToDiscord)
                        .exceptionally(error -> {
                            plugin.getLogger().warning("Failed to start the bot");
                            return null;
                        });
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }

    /**
     * Stops the bot if it has run
     */
    public void stop() {
        if (api != null) {
            api.disconnect();
            api.setReconnectDelay(attempt -> attempt * 2);
            api = null;
        }
    }

    /**
     * Returns the instance created
     */
    public DiscordBot getInstance() {
        return instance;
    }

    public void setOnConnect(Runnable runnable) {
        onConnect = runnable;
    }

    private void onConnect() {
        onConnect.run();
    }

    /**
     * Returns the api in order to change bot settings or add Listeners
     */
    public DiscordApi getApi() {
        return api;
    }

    /**
     * Is run on connect
     */
    public void onConnectToDiscord(DiscordApi api) {
        this.api = api;

        api.addListener(new MessageCreateListener() {
            @Override
            public void onMessageCreate(MessageCreateEvent event) {
                TextChannel channel = event.getChannel();

                if (event.getMessageContent().toLowerCase().startsWith(prefix)) {
                    String[] args = event.getMessageContent().substring(prefix.length()).split(" ");

                    if (args.length >= 1) {
                        getCommandManager().perform(args[0], event.getMessageAuthor().asUser().get(), channel, event.getMessage());
                    }
                }
            }
        });

        audioManager = new AudioManager(this.api);

        onConnect();


        try {
            User owner = api.getOwner().get();
            owner.sendMessage("Your bot just started! Name: " + api.getYourself().getDiscriminatedName());
            owner.sendMessage("Your bot is connected to the server with the Ip: " + plugin.getServer().getIp());
            owner.sendMessage("The Server is using the WuselUtils Plugin to handle Discord Bots");
            owner.sendMessage("The Plugin that is using WuselUtils: " + plugin.getName());
            plugin.getLogger().info("Connected to Discord as " + api.getYourself().getDiscriminatedName());
            plugin.getLogger().info("Open the following url to invite the bot: " + api.createBotInvite());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }
}