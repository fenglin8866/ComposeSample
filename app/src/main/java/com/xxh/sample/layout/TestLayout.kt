package com.xxh.sample.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xxh.sample.common.data.Message
import com.xxh.sample.R


@Composable
fun TestLayout(modifier: Modifier = Modifier) {
    /*LazyColumn(modifier = modifier) {
        items(SampleData.conversationSample) {
            MsgCard(msg = it, modifier = modifier)
        }
    }*/
    Box {
        var imageHeightPx by remember { mutableStateOf(0) }

        Image(
            painter = painterResource(R.drawable.avatar_3),
            contentDescription = "I'm above the text",
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { size ->
                    // Don't do this
                    imageHeightPx = size.height
                }
        )

        Text(
            text = "I'm below the image",
            modifier = Modifier.padding(
                top = with(LocalDensity.current) { imageHeightPx.toDp() }
            )
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MsgCard(msg: Message, modifier: Modifier) {
    var isCick by remember {
        mutableStateOf(false)
    }
    
    /*var surfaceColor by animateColorAsState(
    if(isCick) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )*/
    
    Row(modifier = modifier.padding(bottom = 10.dp)) {
        Image(
            //painter = painterResource(id = R.drawable.avatar_3),
            Icons.Default.Home,
            contentDescription = "",
            modifier = modifier
                .padding(end = 10.dp)
                .size(50.dp)
                .clip(shape = CircleShape)
                .background(Color.Green)

        )
        Surface(modifier = modifier.fillMaxSize(), color = Color.Blue, onClick = {
            isCick = !isCick;
        }) {
            Column {
                Text(text = msg.author)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = msg.body,
                    maxLines = if (!isCick) 1 else Int.MAX_VALUE
                )
            }
        }
    }
}

