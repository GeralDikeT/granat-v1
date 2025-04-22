package com.example.granatv1

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.granatv1.modules.GranatPlayer
import com.example.granatv1.modules.GranatSongRepository
import com.example.granatv1.ui.AppNavHost
import com.example.granatv1.ui.SongPage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Build


class MainActivity : ComponentActivity() {
    val activityPermissionProvider = ActivityPermissionsProvider(this)

    companion object {
        lateinit var player: GranatPlayer
        val songs = GranatSongRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        player = GranatPlayer(this)

        if (activityPermissionProvider.isAudioPermissionGranted()) {
            activityPermissionProvider.accessAudioLibrary()
        } else {
            activityPermissionProvider.requestAudioPermission()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Media Playback"
            val descriptionText = "Notifications for media playback"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel("media_playback_channel", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        installSplashScreen()

        songs.loadSongs(this)

        MainScope().launch {
            songs.loadAlbumArtsAsync()
        }

        setContent {
            val navController = rememberNavController()
            AppNavHost(navController)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    SongPage(navController)
}
