package com.example.granatv1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.granatv1.MainActivity
import com.example.granatv1.R

@Composable
fun SongPageNavigation(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.TopStart).padding(start = 6.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.down_arrow),
                contentDescription = "downArrow",
                modifier = Modifier.size(35.dp)
            )
        }

        IconButton(
            onClick = { println("DotsClicked") },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dots_icon_white),
                contentDescription = "dotsIcon",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}
