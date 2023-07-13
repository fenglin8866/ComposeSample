package com.xxh.sample.ui

import androidx.compose.runtime.Composable
import com.main.data.SampleData
import com.main.ui.ListScreen

@Composable
fun TestMainScreen(onClickAction: (String) -> Unit) {
    ListScreen(data = SampleData.conversationSample3) {
        onClickAction(it)
    }
}
