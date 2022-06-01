/*
 * Copyright (c) 2022.
 * Class Copyright is owned by Wusel
 */

package me.wusel.wuselutils.discord.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.audio.AudioSource;

public class AudioData {

    private AudioPlayer audioPlayer;
    private AudioPlayerManager playerManager;
    private AudioSource audioSource;
    private AudioConnection audioConnection;
    private AudioPlaylist audioPlaylist;

    public AudioData(AudioPlayer audioPlayer, AudioPlayerManager playerManager, AudioSource audioSource, AudioConnection audioConnection, AudioPlaylist audioPlaylist) {
        this.audioPlayer = audioPlayer;
        this.playerManager = playerManager;
        this.audioSource = audioSource;
        this.audioConnection = audioConnection;
        this.audioPlaylist = audioPlaylist;

    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public void setAudioPlayer(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    public AudioPlayerManager getPlayerManager() {
        return playerManager;
    }

    public void setPlayerManager(AudioPlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public AudioSource getAudioSource() {
        return audioSource;
    }

    public void setAudioSource(AudioSource audioSource) {
        this.audioSource = audioSource;
    }

    public AudioConnection getAudioConnection() {
        return audioConnection;
    }

    public void setAudioConnection(AudioConnection audioConnection) {
        this.audioConnection = audioConnection;
    }

    public AudioPlaylist getAudioPlaylist() {
        return audioPlaylist;
    }

    public void setAudioPlaylist(AudioPlaylist audioPlaylist) {
        this.audioPlaylist = audioPlaylist;
    }
}
