package com.xxh.sample.others.compositionlocal

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LocalElevationsSample() {
    // Calculate elevations based on the system theme
    val elevations = if (isSystemInDarkTheme()) {
        Elevations(3.dp, default = 3.dp)
    } else {
        Elevations(10.dp, default = 10.dp)
    }
    CompositionLocalProvider(LocalElevations provides elevations) {
        Card(
            backgroundColor = MaterialTheme.colors.onError,
            elevation = LocalElevations.current.card,
            modifier = Modifier
                .size(150.dp)
                .padding(20.dp)
        ) {
            // Content
            Text("测试", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
        }

    }
}