package com.xxh.sample

import androidx.compose.runtime.Composable
import com.common.ListScreen
import com.xxh.sample.images.ImageTest

@Composable
fun TestMainScreen(data: List<String>, onClickAction: (String) -> Unit) {
    // MessageCard(Message("Hi", "今天天气不错哦"))
    // HelloContent2()
    // WellnessScreenB()
    // AnimTest()
    //HelloContent()
    // TestLayout()
    //ImageTest()
    ListScreen(data = data, { return@ListScreen it }) {
        onClickAction(it)
    }
}
