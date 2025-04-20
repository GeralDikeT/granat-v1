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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.granatv1.R

@Composable
fun ButtonsBar() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.random_icon),
                contentDescription = "Random",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(35.dp)
            )
        }

        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.previous_song_white),
                contentDescription = "Previous",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(35.dp)
            )
        }

        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.play_button_icon),
                contentDescription = "Play",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(45.dp)
            )
        }

        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.next_song_white),
                contentDescription = "Next",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(35.dp)
            )
        }

        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.favorite_white),
                contentDescription = "Favorite",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(35.dp)
            )
        }
    }
}