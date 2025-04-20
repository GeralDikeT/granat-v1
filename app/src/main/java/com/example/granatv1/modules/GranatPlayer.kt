package com.example.granatv1.modules

import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.granatv1.MainActivity
import kotlin.random.Random

class GranatPlayer {
    var mediaPlayer: MediaPlayer = MediaPlayer();
    var currentSong: GranatSong? = null;

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
            nextSongIndex = Random.nextInt(0, MainActivity.songs.list.size);
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

    fun playSong(song: GranatSong) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop();
        }

        currentSong = song
        mediaPlayer.reset()
        mediaPlayer.setDataSource(song.path);
        mediaPlayer.prepare()
        mediaPlayer.start();
    }



    fun pause() {
        mediaPlayer.pause();
    }
    fun resume() {
        mediaPlayer.start();
    }
}