package com.example.granatv1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.granatv1.MainActivity
import com.example.granatv1.modules.GranatSong
import com.example.granatv1.R
import com.example.granatv1.ui.SongPage

@Composable
fun BottomSongBar(modifier: Modifier = Modifier, onSongClick: () -> Unit) {
    val isPlayerPaused = MainActivity.player.isPaused

    val song = MainActivity.player.currentSong!!

    val artistAndAlbumInfo = song.artist + " | " + song.albumTitle

    Box(
        modifier
            .fillMaxWidth()
            .clickable() {
                onSongClick()
            }
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color(0xFFE1D4D4), shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (song.albumArt != null) {
                        Image(
                            bitmap = song.albumArt.asImageBitmap(),
                            contentDescription = "Current song cover",
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(24.dp))
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.no_cover),
                            contentDescription = "Current song cover",
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(24.dp))
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column() {
                        Text(
                            text = song.title,
                            color = Color(0xFF924A4A),
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.widthIn(0.dp, 175.dp)
                        )
                        Text(
                            text = artistAndAlbumInfo,
                            color = Color.Gray,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.widthIn(0.dp, 175.dp)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(-8.dp)
                ) {
                    IconButton(onClick = { MainActivity.player.playPreviusSong() }) {
                        Image(
                            painter = painterResource(id = R.drawable.previussong_icon),
                            contentDescription = "previusSong",
                            modifier = Modifier
                                .size(25.dp)
                                .requiredWidth(25.dp)
                        )
                    }
                    IconButton(onClick = {
                        if (MainActivity.player.isPlaying()) {
                            MainActivity.player.pause();
                        }
                        else {
                            MainActivity.player.resume();
                        }
                    }) {
                        val icon = if (!isPlayerPaused) {
                            R.drawable.pause_icon
                        } else {
                            R.drawable.play_sign
                        }
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = "Play/Pause",
                            modifier = Modifier
                                .size(25.dp)
                                .requiredWidth(25.dp)
                        )
                    }
                    IconButton(onClick = {  MainActivity.player.playNextSong(true) }) {
                        Image(
                            painter = painterResource(id = R.drawable.nextsong_icon),
                            contentDescription = "nextSong",
                            modifier = Modifier
                                .size(25.dp)
                                .requiredWidth(25.dp)
                        )
                    }
                }
            }
        }
    }
}
