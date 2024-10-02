package com.example.day3practice.BasicJetpackCompose

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SimpleAnimation() {
    var sizeState = remember { mutableStateOf(200.dp) }
    var size = animateDpAsState(
        targetValue = sizeState.value,
        tween(durationMillis = 1000)
    )

    val color = rememberInfiniteTransition().animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier.size(size.value)
            .background(color.value),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            sizeState.value += 50.dp
        }) {
            Text(text = "Increase")
        }
    }
}
