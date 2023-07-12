package com.xxh.sample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.main.ListScreen
import com.main.SampleData
import com.xxh.sample.ui.theme.SampleAppTheme

@Composable
fun TestDemoScreen() {
    SampleAppTheme {
        TestScreen(modifier = Modifier.fillMaxSize())
    }
}


@Composable
fun TestScreen(modifier: Modifier = Modifier) {
    ListScreen(data = SampleData.conversationSample3) {

    }
}
