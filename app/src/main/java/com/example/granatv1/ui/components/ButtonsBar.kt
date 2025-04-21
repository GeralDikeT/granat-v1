package com.example.granatv1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.granatv1.MainActivity
import com.example.granatv1.R
import com.example.granatv1.modules.GranatPlayer
import com.example.granatv1.modules.loadFavoriteStatus
import kotlin.random.Random

@Composable
fun ButtonsBar() {
    val isPlayerPaused = MainActivity.player.isPaused

    val isFavorite = MainActivity.player.currentSong!!.isFavorite

    val FIcon = if (isFavorite) R.drawable.favorite_icon_gold else R.drawable.favorite_white

    val icon = when (MainActivity.player.mode) {
        GranatPlayer.ModeState.Normal -> R.drawable.normal_mode_icon
        GranatPlayer.ModeState.Random -> R.drawable.random_icon
        GranatPlayer.ModeState.Loop -> R.drawable.loop_icon
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        IconButton(
            onClick = {
                MainActivity.player.mode = MainActivity.player.nextMode(MainActivity.player.mode)
            }
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Random",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(35.dp)
            )
        }

        IconButton(
            onClick = { MainActivity.player.playPreviusSong() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.previous_song_white),
                contentDescription = "Previous",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(35.dp)
            )
        }

        IconButton(
            onClick = {
                if (MainActivity.player.isPlaying()) {
                    MainActivity.player.pause();
                }
                else {
                    MainActivity.player.resume();
                }
            }
        ) {
            val icon = if (!isPlayerPaused) {
                R.drawable.pause_icon
            } else {
                R.drawable.play_sign
            }
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Play",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(45.dp)
            )
        }

        IconButton(
            onClick = { MainActivity.player.playNextSong(true) }
        ) {
            Image(
                painter = painterResource(id = R.drawable.next_song_white),
                contentDescription = "Next",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(35.dp)
            )
        }

        IconButton(
            onClick = {
                MainActivity.player.currentSong!!.let {
                    it.isFavorite = !it.isFavorite
                }
            }
        ) {
            Image(
                painter = painterResource(id = FIcon),
                contentDescription = "Favorite",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}