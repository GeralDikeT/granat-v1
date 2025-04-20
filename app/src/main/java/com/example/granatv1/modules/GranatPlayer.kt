package com.example.granatv1.modules

import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.granatv1.MainActivity
import kotlin.random.Random

class GranatPlayer {
    var mediaPlayer: MediaPlayer = MediaPlayer();
    var currentSong by mutableStateOf<GranatSong?>(null)

    var isPaused by mutableStateOf(false)

    var isRandom = false;

    init {
        mediaPlayer.setOnCompletionListener { afterSong() };
    }

    fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying;
    }

    fun afterSong() {
        playNextSong();
    }

    fun playNextSong() {
        val currentSongIndex = MainActivity.songs.list.indexOfFirst { song -> song === currentSong }
        var nextSongIndex = 0;

        if (isRandom) {
            playSong(MainActivity.songs.getRandomSong());
            return;
        }
        else {
            nextSongIndex = currentSongIndex + 1;
        }

        if (nextSongIndex >= MainActivity.songs.list.count()) {
            nextSongIndex = 0;
        }

        val nextSong = MainActivity.songs.list[nextSongIndex];
        playSong(nextSong);
    }

    fun playPreviusSong() {
        val currentSongIndex = MainActivity.songs.list.indexOfFirst { song -> song === currentSong }
        var previusSongIndex = 0;

        previusSongIndex = currentSongIndex - 1;


        if (previusSongIndex == -1) {
            previusSongIndex = MainActivity.songs.list.count() - 1;
        }

        val previusSong = MainActivity.songs.list[previusSongIndex];
        playSong(previusSong);
    }

    fun playSong(song: GranatSong) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop();
        }

        currentSong = song
        mediaPlayer.reset()
        mediaPlayer.setDataSource(song.path);
        mediaPlayer.prepare()
        mediaPlayer.start();
        isPaused = false
    }

    fun playRandomSong() {
        playSong(MainActivity.songs.getRandomSong());
    }

    fun pause() {
        mediaPlayer.pause();
        isPaused = true
    }
    fun resume() {
        mediaPlayer.start();
        isPaused = false
    }
}