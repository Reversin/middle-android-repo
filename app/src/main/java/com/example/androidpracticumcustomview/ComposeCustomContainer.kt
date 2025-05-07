package com.example.androidpracticumcustomview

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun ComposeCustomViewGroup(
    topContent: @Composable () -> Unit,
    bottomContent: @Composable (() -> Unit)? = null
) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    var animate by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        animate = true
    }

    val topAlpha by animateFloatAsState(
        targetValue = if (animate) 1f else 0f,
        animationSpec = tween(durationMillis = 5000)
    )
    val bottomAlpha by animateFloatAsState(
        targetValue = if (animate) 1f else 0f,
        animationSpec = tween(durationMillis = 5000)
    )
    val topOffsetY by animateDpAsState(
        targetValue = if (animate) 0.dp else screenHeight / 2,
        animationSpec = tween(durationMillis = 5000)
    )
    val bottomOffsetY by animateDpAsState(
        targetValue = if (animate) 0.dp else -screenHeight / 2,
        animationSpec = tween(durationMillis = 5000)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = topOffsetY)
                .alpha(topAlpha)
        ) {
            topContent()
        }
        bottomContent?.let {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = bottomOffsetY)
                    .alpha(bottomAlpha)
            ) {
                it()
            }
        }
    }
}
