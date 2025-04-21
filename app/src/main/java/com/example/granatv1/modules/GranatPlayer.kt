package com.example.granatv1.modules

import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.granatv1.MainActivity
import com.example.granatv1.ui.SongPage
import kotlin.random.Random

class GranatPlayer {
    var mediaPlayer: MediaPlayer = MediaPlayer();
    var currentSong by mutableStateOf<GranatSong?>(null)

    var isPaused by mutableStateOf(false)

    enum class ModeState {
        Normal,
        Random,
        Loop
    }

    fun nextMode(currentMode: ModeState): ModeState {
        val values = GranatPlayer.ModeState.entries
        val currentIndex = values.indexOf(currentMode)

        val nextIndex = (currentIndex + 1) % values.size

        return values[nextIndex]
    }


    var mode by mutableStateOf(ModeState.Normal)

    init {
        mediaPlayer.setOnCompletionListener { afterSong() };
    }

    fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying;
    }

    fun afterSong() {
        playNextSong();
    }

    fun playNextSong(nextByButton: Boolean = false) {
        val currentSongIndex = MainActivity.songs.list.indexOfFirst { song -> song === currentSong }
        var nextSongIndex = 0;

        if (nextByButton && mode == ModeState.Loop) {
            nextSongIndex = currentSongIndex + 1;
            if (nextSongIndex >= MainActivity.songs.list.count()) {
                nextSongIndex = 0;
            }
            val nextSong = MainActivity.songs.list[nextSongIndex];
            playSong(nextSong);
        }
        when (mode) {
            ModeState.Random -> playSong(MainActivity.songs.getRandomSong())
            ModeState.Loop -> playSong(currentSong!!)
            ModeState.Normal -> {
                nextSongIndex = currentSongIndex + 1;
                if (nextSongIndex >= MainActivity.songs.list.count()) {
                    nextSongIndex = 0;
                }
                val nextSong = MainActivity.songs.list[nextSongIndex];
                playSong(nextSong);
            }
        }
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
            if (song == currentSong) {
                return
            }
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

    fun getCurrentPosition(): Int {
        return mediaPlayer.currentPosition
    }
}