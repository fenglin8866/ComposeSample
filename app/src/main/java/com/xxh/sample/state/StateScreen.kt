package com.xxh.sample.state

import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.common.ListScreenString
import com.xxh.sample.state.codelabs.WellnessScreen

@Composable
fun StateScreen() {
    ShowWay2()
}

/**
 * 跳转页面的方式1：通过State变量变化重组
 */
@Composable
fun ShowWay1() {
    var showMain by remember {
        mutableStateOf("home")
    }
    if (showMain == "home") {
        ListScreenString(data = stateListData) {
            showMain = it
        }
    } else if (showMain == "state_codelab") {
        WellnessScreen()
    }
}

/**
 * 跳转页面的方式2：通过Navigation  推荐使用
 */
@Composable
fun ShowWay2() {
    StateNavGraph(rememberNavController())
}

