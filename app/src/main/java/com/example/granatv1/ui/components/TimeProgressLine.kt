package com.example.granatv1.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import com.example.granatv1.R
import com.example.granatv1.modules.GranatPlayer


@Composable
fun TimeProgressLine() {
    var currentPosition by remember { mutableStateOf(MainActivity.player.mediaPlayer.currentPosition) }
    val maxProgress = MainActivity.player.currentSong!!.duration

    if (!MainActivity.player.isPaused) {
        LaunchedEffect(Unit) {
            while (true) {
                delay(100L)
                currentPosition = MainActivity.player.mediaPlayer.currentPosition
            }
        }

    }

    val durationInSec = maxProgress / 1000
    val minutes = durationInSec / 60
    val seconds = durationInSec % 60

    val progress = currentPosition.toFloat() / maxProgress.toFloat()


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures { tapOffset ->
                    val clickedPosition = (tapOffset.x / 960) * maxProgress.toFloat()

                    MainActivity.player.seekSongTo(clickedPosition.toInt())
                }
            }
        ) {
            Box(
            modifier = Modifier
                .width(320.dp)
                .height(2.dp)
                .background(colorResource(id = R.color.gray))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress.coerceIn(0f, 1f))
                    .background(colorResource(id = R.color.white))
            )
        }
        }


        Row(
            modifier = Modifier
                .width(320.dp)
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatTime(currentPosition),
                fontSize = 14.sp,
                color = colorResource(id = R.color.gray)
            )
            Text(
                text = String.format("%02d:%02d", minutes, seconds),
                fontSize = 14.sp,
                color = colorResource(id = R.color.gray)
            )
        }
    }
}

private fun formatTime(ms: Int): String {
    val totalSec = ms / 1000
    val min = totalSec / 60
    val sec = totalSec % 60
    return String.format("%d:%02d", min, sec)
}
