package com.example.granatv1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.granatv1.MainActivity
import com.example.granatv1.ui.components.ButtonsBar
import com.example.granatv1.ui.components.SongInfoDiv
import com.example.granatv1.ui.components.SongPageNavigation
import com.example.granatv1.ui.components.TimeProgressLine

@Composable
fun SongPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(Color.Black)
            .padding(30.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SongPageNavigation()
        SongInfoDiv()
        Spacer(modifier = Modifier.padding(top = 75.dp))
        TimeProgressLine()
        Spacer(modifier = Modifier.padding(top = 75.dp))
        ButtonsBar()

    }
}