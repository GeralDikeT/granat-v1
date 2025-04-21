package com.example.granatv1.ui.components

import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.colorResource


fun durationCalculate(duration: Int) : String {
    val duration = duration / 1000
    val minutes = duration / 60
    val seconds = duration % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Composable
fun SongItem(granatSong: GranatSong, modifier: Modifier, onSongClick: (GranatSong) -> Unit) {
    val artistAndAlbumInfo = granatSong.artist + " | " + granatSong.albumTitle

    val shortTitle = if (granatSong.title.length > 15) granatSong.title.take(15) + "..." else granatSong.title
    val shortArtistAndAlbumInfo = if (artistAndAlbumInfo.length > 15) granatSong.artist.take(15) + "..." else granatSong.artist


    Box(modifier
        .clickable() {
            onSongClick(granatSong)
        }) {
        Box(
            modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.white), shape = RoundedCornerShape(4.dp))
                .padding(6.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(

                ) {
                    if (granatSong.albumArt != null) {
                        Image(
                            bitmap = granatSong.albumArt.asImageBitmap(),
                            contentDescription = "songCover",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(shape = RoundedCornerShape(4.dp))
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.no_cover),
                            contentDescription = "songCover",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(shape = RoundedCornerShape(4.dp))
                        )
                    }
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column(
                    modifier.weight(1f)
                ) {
                    Text(
                        text = shortTitle,
                        color = colorResource(id = R.color.black),
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier.height(22.dp).width(IntrinsicSize.Max)
                    ) {
                        Text(
                            text = durationCalculate(granatSong.duration),
                            color = colorResource(id = R.color.gray),
                            fontSize = 14.sp
                        )
                        Spacer(modifier.width(4.dp))
                        Text(
                            text = shortArtistAndAlbumInfo,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Box(

                ) {
                    IconButton(onClick = { println("SongSettingsClicked") },
                        modifier = Modifier
                            .size(28.dp))
                    {
                        Image(
                            painter = painterResource(id = R.drawable.dots_icon),
                            contentDescription = "SongSettings",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }
    }
}
