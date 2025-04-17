package com.example.granatv1.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.granatv1.R

@Composable
fun HorizontalCardsList() {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .height(55.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HorizontalCardButton("Favorite", R.drawable.favorite_icon, Modifier.weight(1f))
        HorizontalCardButton("Playlists", R.drawable.playlist_icon, Modifier.weight(1f))
        HorizontalCardButton("Soon", R.drawable.question_sign_icon, Modifier.weight(1f))
    }
}
