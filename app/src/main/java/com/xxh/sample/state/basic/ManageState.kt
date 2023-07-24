package com.xxh.sample.state.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString

@Composable
fun ManageState() {
    ChatBubble()
}

/**
 * 不需要状态提升
 * 其他可组合项不需要控制状态时
 */
@Composable
fun ChatBubble() {
    var showDetails by remember {
        mutableStateOf(false)
    }
    Column {
        ClickableText(
            text = AnnotatedString("自力更生"),
            onClick = { showDetails = !showDetails } // Apply simple UI logic
        )
        if (showDetails) {
            Text("只有自己的努力奋斗，才能改变自己，才能突破自我")
        }
    }

}