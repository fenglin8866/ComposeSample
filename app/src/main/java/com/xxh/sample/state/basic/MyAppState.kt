package com.xxh.sample.state.basic

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember


/**
 * 状态容器示例
 */
@Composable
fun rememberMyAppState(
    windowSizeClass: WindowSizeClass
): MyAppState {
    return remember(windowSizeClass) {
        MyAppState(windowSizeClass)
    }
}

@Stable
class MyAppState(
    private val windowSizeClass: WindowSizeClass
) {

}