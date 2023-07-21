package com.xxh.sample.state.basic

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.xxh.sample.common.component.XDivider


@Composable
fun StateBasicScreen() {
    Column {
        Log.i("xxh11","StateBasicScreen")
        OtherState()
        XDivider()
        HelloScreen()
        XDivider()
        ShowCities()
        XDivider()
        BackgroundBanner()
    }
}