package com.xxh.sample.state.basic

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xxh.sample.common.component.XDivider


@Composable
fun StateBasicScreen() {
    var showScreen by remember {
        mutableStateOf("main")
    }
    Column {
        Row {
            Button(onClick = { showScreen = "main" }, modifier = Modifier.padding(5.dp)) {
                Text(text = "主页")
            }
            Button(onClick = { showScreen = "manage" }, modifier = Modifier.padding(5.dp)) {
                Text(text = "状态管理")
            }
            Button(onClick = { showScreen = "save" }, modifier = Modifier.padding(5.dp)) {
                Text(text = "状态保存")
            }
        }

        when (showScreen) {
            "main" -> StateBasicMain()
            "manage" -> ManageState()
            "save" -> ConversationScreen()
        }
    }
}

@Composable
fun StateBasicMain() {
    Column {
        Log.i("xxh11", "StateBasicScreen")
        OtherState()
        XDivider()
        HelloScreen()
        XDivider()
        ShowCities()
        XDivider()
        BackgroundBanner()
    }
}

