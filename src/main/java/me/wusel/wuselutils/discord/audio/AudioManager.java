/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.discord.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.javacord.api.DiscordApi;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;

import java.util.HashMap;

public class AudioManager {

    private HashMap<Server, AudioData> connections;

    private DiscordApi api;

    public AudioManager(DiscordApi api) {
        connections = new HashMap<>();
        this.api = api;
    }

    public void playAudio(String audioLink, TextChannel channel, AudioConnection audioConnection, Server server, AudioSourceType audioSourceType, int volume) {
        AudioPlayerManager playerManager =  new DefaultAudioPlayerManager();
        switch (audioSourceType) {
            case TWITCH -> playerManager.registerSourceManager(new TwitchStreamAudioSourceManager());
            case HTTP -> playerManager.registerSourceManager(new HttpAudioSourceManager());
            default -> playerManager.registerSourceManager(new YoutubeAudioSourceManager());
        }

        AudioPlayer player = playerManager.createPlayer();
        System.out.println("Created player");

        player.setVolume(volume);

        AudioSource source = new LavaplayerAudioSource(this.api, player);
        audioConnection.setAudioSource(source);

        final AudioPlaylist[] playlist = {null};

        playerManager.loadItem(audioLink, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                player.playTrack(audioTrack);
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                playlist[0] = audioPlaylist;
                for (AudioTrack track : audioPlaylist.getTracks()) {
                    player.playTrack(track);
                }
            }

            @Override
            public void noMatches() {
                channel.sendMessage("Video not found!");
            }

            @Override
            public void loadFailed(FriendlyException e) {
                channel.sendMessage("Error loading video!");
            }
        });
        connections.put(server, new AudioData(player, playerManager, source, audioConnection, playlist[0]));
    }

    public boolean isPlaying(Server server) {
        return connections.containsKey(server);
    }

    public void stopAudio(Server server) {
        if (isPlaying(server)) {
            AudioConnection connection = connections.get(server).getAudioConnection();
            connection.close();
            connections.remove(server);
        }
    }

    public void setAudioSource(Server server, AudioSource source) {
        if (isPlaying(server)) {
            AudioConnection connection = connections.get(server).getAudioConnection();
            connection.setAudioSource(source);
        }
    }

    public AudioData getAudioData(Server server) {
        if (isPlaying(server))
            return connections.get(server);
        return null;
    }
}
