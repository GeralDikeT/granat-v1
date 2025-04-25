package com.example.granatv1.modules

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat
import androidx.media.app.NotificationCompat.MediaStyle
import com.example.granatv1.MainActivity
import com.example.granatv1.MainActivity.Companion.songs
import com.example.granatv1.MediaControlReceiver
import com.example.granatv1.R

class GranatPlayer(private val context: Context) {
    var mediaPlayer: MediaPlayer = MediaPlayer();
    var currentSong by mutableStateOf<GranatSong?>(null)

    private var mediaSession: MediaSessionCompat? = null

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
        mediaPlayer.setOnCompletionListener { afterSong() }
    }

    fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    fun afterSong() {
        playNextSong()
    }

    fun playNextSong(nextByButton: Boolean = false) {
        val currentSongIndex = songs.list.indexOfFirst { song -> song === currentSong }
        var nextSongIndex = 0

        if (nextByButton && mode == ModeState.Loop) {
            nextSongIndex = currentSongIndex + 1
            if (nextSongIndex >= songs.list.count()) {
                nextSongIndex = 0
            }
            val nextSong = songs.list[nextSongIndex]
            playSong(nextSong)
        }
        when (mode) {
            ModeState.Random -> playSong(songs.getRandomSong())
            ModeState.Loop -> playSong(currentSong!!)
            ModeState.Normal -> {
                nextSongIndex = currentSongIndex + 1
                if (nextSongIndex >= songs.list.count()) {
                    nextSongIndex = 0
                }
                val nextSong = songs.list[nextSongIndex]
                playSong(nextSong)
            }
        }
    }

    fun playPreviusSong() {
        val currentSongIndex = songs.list.indexOfFirst { song -> song === currentSong }
        var previusSongIndex = 0

        previusSongIndex = currentSongIndex - 1

        if (previusSongIndex == -1) {
            previusSongIndex = songs.list.count() - 1
        }

        val previusSong = songs.list[previusSongIndex]
        playSong(previusSong)
    }

    fun playSong(song: GranatSong) {
        if (mediaPlayer.isPlaying) {
            if (song == currentSong) {
                return
            }
            mediaPlayer.stop()
        }

        currentSong = song
        mediaPlayer.reset()
        mediaPlayer.setDataSource(song.path)
        mediaPlayer.prepare()
        mediaPlayer.start()
        isPaused = false

        createNotification()
    }


    fun playRandomSong() {
        playSong(songs.getRandomSong())
    }

    fun pause() {
        mediaPlayer.pause()
        isPaused = true
        createNotification()
    }

    fun resume() {
        mediaPlayer.start()
        isPaused = false
        createNotification()
    }

    fun createNotification() {
        if (mediaSession == null) {
            mediaSession = MediaSessionCompat(context, "MyMediaSession")
        }

        mediaSession!!.setMetadata(
            MediaMetadataCompat.Builder()
                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, currentSong!!.title ?: "nothing")
                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, currentSong!!.artist ?: "nothing")
                .build()
        )

        val prevIntent = Intent(context, MediaControlReceiver::class.java).apply {
            action = "ACTION_PREV"
        }
        val prevPendingIntent = PendingIntent.getBroadcast(context, 12, prevIntent, PendingIntent.FLAG_IMMUTABLE)

        val pauseIntent = Intent(context, MediaControlReceiver::class.java).apply {
            action = "ACTION_PAUSE"
        }
        val pausePendingIntent = PendingIntent.getBroadcast(context, 10, pauseIntent, PendingIntent.FLAG_IMMUTABLE)

        val nextIntent = Intent(context, MediaControlReceiver::class.java).apply {
            action = "ACTION_NEXT"
        }
        val nextPendingIntent = PendingIntent.getBroadcast(context, 11, nextIntent, PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(context, "media_playback_channel")
            .setSmallIcon(R.drawable.star_ic_notification)
            .setLargeIcon(currentSong!!.albumArt)
            .setContentTitle("Currently Playing")
            .setContentText(currentSong!!.title ?: "nothing")
            .addAction(R.drawable.previous_song_icon_notification, "previousSong", prevPendingIntent)
            .addAction(
                if (MainActivity.player.isPaused) R.drawable.play_button_ic_notification else R.drawable.stop_icon_notification,
                "play/Pause", pausePendingIntent
            )
            .addAction(R.drawable.next_song_icon_notification, "nextSong", nextPendingIntent)
            .setStyle(
                MediaStyle()
                    .setMediaSession(mediaSession!!.sessionToken)
                    .setShowActionsInCompactView(0, 1, 2)
            )
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOngoing(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

    fun getCurrentPosition(): Int {
        return mediaPlayer.currentPosition
    }
}
