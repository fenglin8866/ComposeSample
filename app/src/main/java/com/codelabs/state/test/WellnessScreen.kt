package com.codelabs.state.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        var showList by remember {
            mutableStateOf(false)
        }
        Button(
            onClick = { showList = !showList },
            modifier = modifier.padding(start = 16.dp)
        ) {
            Text(text = if (showList) "隐藏列表" else "显示列表")
        }
        Divider(
            modifier = modifier
                .height(6.dp)
                .fillMaxWidth()
        )
        if (showList) {
            WellnessTaskList(modifier)
        } else {
            WaterCounter(modifier)
            Divider(
                modifier = modifier
                    .height(6.dp)
                    .fillMaxWidth()
            )
            StatefulCounter(modifier)
            Divider(
                modifier = modifier
                    .height(6.dp)
                    .fillMaxWidth()
            )
        }
    }
}


