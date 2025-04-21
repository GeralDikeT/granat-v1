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


class MainActivity : ComponentActivity() {
    companion object {
        val songs = GranatSongRepository();
        val player = GranatPlayer();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isAudioPermissionGranted()) {
            accessAudioLibrary()
        } else {
            requestAudioPermission()
        }

        installSplashScreen()

        songs.loadSongs(this);

        setContent {
            val navController = rememberNavController()
            AppNavHost(navController)
        }
    }
    private fun isAudioPermissionGranted() : Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestAudioPermission() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.READ_MEDIA_AUDIO)
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        requestPermissionLauncher.launch(permissions)
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {permission ->
        val audioPermissionGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission[Manifest.permission.READ_MEDIA_AUDIO] ?: false
        } else {
            permission[Manifest.permission.READ_EXTERNAL_STORAGE] ?: false
        }

        if (audioPermissionGranted) {
            accessAudioLibrary()
        } else {

            Toast.makeText(this, "Audio Permission Granted", Toast.LENGTH_LONG).show()
        }
    }

    private fun accessAudioLibrary() {
        return
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    SongPage(navController)
}