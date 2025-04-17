package com.example.granatv1.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HorizontalCardButton(header: String, imageId: Int, modifier: Modifier = Modifier) {
    Box(modifier) {
        Box(modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(9.dp))
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable() {
                println("Box Clicked")
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp, top = 8.dp, bottom = 3.dp),
            ) {

                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "Settings",
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = header,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
