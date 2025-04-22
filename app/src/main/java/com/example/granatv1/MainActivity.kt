package com.example.granatv1

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import android.os.Build
import androidx.core.content.ContextCompat
import android.Manifest
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.granatv1.modules.GranatPlayer
import com.example.granatv1.modules.GranatSongRepository
import com.example.granatv1.ui.AppNavHost
import com.example.granatv1.ui.MainPage
import com.example.granatv1.ui.SongPage
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    val activityPermissionProvider = ActivityPermissionsProvider(this)

    companion object {
        val songs = GranatSongRepository();
        val player = GranatPlayer();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (activityPermissionProvider.isAudioPermissionGranted()) {
            activityPermissionProvider.accessAudioLibrary()
        } else {
            activityPermissionProvider.requestAudioPermission()
        }

        installSplashScreen()

        songs.loadSongs(this);

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