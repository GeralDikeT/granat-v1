package com.example.granatv1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MediaControlReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "ACTION_PREV" -> {
                MainActivity.player.playPreviusSong()
            }
            "ACTION_PAUSE" -> {
                if (MainActivity.player.isPaused) {
                    MainActivity.player.resume()
                } else {
                    MainActivity.player.pause()
                }
            }
            "ACTION_NEXT" -> {
                MainActivity.player.playNextSong()
            }
//            "MODE_CHANGE" -> {
//                MainActivity.player.playNextSong()
//            }
//            "FAVORITE_STATE_CHANGE" -> {
//                MainActivity.player.playNextSong()
//            }
        }
    }
}
