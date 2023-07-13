package com.xxh.sample.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.xxh.sample.R

@Composable
fun TestCompose() {
    Box(modifier = Modifier.fillMaxWidth()){
        Text(text = "")
    }

/*    val color2 = animateColorAsState(if (condition) Color.Green else Color.Red)

    val color = remember { Animatable(Color.Gray) }

    LaunchedEffect(condition) {
        color.animateTo(if (condition) Color.Green else Color.Red)
    }*/

    //runtime:

    Box {
        var imageHeightPx by remember { mutableStateOf(0) }

        Image(
            painter = painterResource(R.drawable.ali),
            contentDescription = "I'm above the text",
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { size ->
                    // Don't do this
                    imageHeightPx = size.height
                }
        )

        Text(
            text = "", modifier = Modifier.padding(
                top = with(LocalDensity.current) {
                    imageHeightPx.toDp()
                })
        )
    }

}

@Composable
fun MyAppTopAppBar(topAppBarText: String, onBackPressed: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "localizedString"
                )
            }
        },
        // ...
    )
}




