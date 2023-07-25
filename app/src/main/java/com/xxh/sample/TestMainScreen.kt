package com.xxh.sample

import androidx.compose.runtime.Composable
import com.common.ListScreen
import com.xxh.sample.images.ImageTest

@Composable
fun TestMainScreen(data: List<String>, onClickAction: (String) -> Unit) {
    ListScreen(data = data, { return@ListScreen it }) {
        onClickAction(it)
    }
}
