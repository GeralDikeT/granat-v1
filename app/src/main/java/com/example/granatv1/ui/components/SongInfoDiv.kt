package com.example.granatv1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.granatv1.MainActivity
import com.example.granatv1.R

@Composable
fun SongInfoDiv() {
    val song = MainActivity.songs.list[22]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 40.dp)
    ) {
        if (song.albumArt != null) {
            Image(
                bitmap = song.albumArt.asImageBitmap(),
                contentDescription = "Album Art",
                modifier = Modifier.size(320.dp).clip(shape = RoundedCornerShape(15.dp))
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.thepoeticedda),
                contentDescription = "No Album Art",
                modifier = Modifier.size(320.dp).clip(shape = RoundedCornerShape(15.dp))
            )
        }
        Spacer(modifier = Modifier.padding(6.dp))
        Text(
            text = song.title,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = song.albumTitle,
            color = Color.Gray,
            fontSize = 16.sp
        )
    }
}
