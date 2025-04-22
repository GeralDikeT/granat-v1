package com.example.granatv1

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class ActivityPermissionsProvider(mainActivity: MainActivity) {
    val mainActivity = mainActivity

    public fun isAudioPermissionGranted() : Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
        } else {
            ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        }
    }

    public fun requestAudioPermission() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.READ_MEDIA_AUDIO)
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        requestPermissionLauncher.launch(permissions)
    }

    public val requestPermissionLauncher = mainActivity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->
        val audioPermissionGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permission[Manifest.permission.READ_MEDIA_AUDIO] ?: false
        } else {
            permission[Manifest.permission.READ_EXTERNAL_STORAGE] ?: false
        }

        if (audioPermissionGranted) {
            accessAudioLibrary()
        } else {
            Toast.makeText(mainActivity, "Audio Permission Granted", Toast.LENGTH_LONG).show()
        }
    }

    public fun accessAudioLibrary() {
        return
    }
}