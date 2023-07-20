package com.xxh.sample.state.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.xxh.sample.common.component.XDivider


@Composable
fun StateBasicScreen() {
    Column {
        HelloScreen()
        XDivider()
        ShowCities()
        XDivider()
        BackgroundBanner()
    }
}