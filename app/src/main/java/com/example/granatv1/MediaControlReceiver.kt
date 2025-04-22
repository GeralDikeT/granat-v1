package com.example.granatv1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MediaControlReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "ACTION_PREV" -> {

                Log.d("MediaControlReceiver", "Previous Track")
            }
            "ACTION_PAUSE" -> {

                Log.d("MediaControlReceiver", "Pause")
            }
            "ACTION_NEXT" -> {

                Log.d("MediaControlReceiver", "Next Track")
            }
        }
    }
}
