package com.example.granatv1.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.granatv1.MainActivity
import com.example.granatv1.modules.GranatSongRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimeProgressLine() {
    val currentPosition = MainActivity.player.mediaPlayer.currentPosition
    val maxProgrss = MainActivity.player.currentSong

    Box(

    ) {
        Box(
            modifier = Modifier
                .height(1.8.dp)
                .background(color = Color.Gray)
                .width(320.dp)
        ) {

        }

        Box(
            modifier = Modifier
                .height(1.8.dp)
                .background(color = Color.White)
                .width(160.dp)
        ) {
        }

        Box(
            modifier = Modifier.padding(top = 6.dp).width(320.dp)
        ) {
            Text(
                text = "2:03",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.TopStart).padding(start = 1.dp),
                fontSize = 16.sp
            )

            Text(
                text = "6:03",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.TopEnd).padding(end = 1.dp),
                fontSize = 16.sp
            )
        }

    }


}