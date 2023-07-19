package com.codelabs.state.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    var count by remember {
        mutableIntStateOf(0)
    }
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            var showTask by remember {
                mutableStateOf(true)
            }
            if (showTask) {
                WellnessTaskItem(
                    taskName = "Have you taken your 15 minute walk today?",
                    onClose = { showTask = false })
            }

            Text(
                text = "You've had $count glasses.",
                modifier = modifier.padding(16.dp)
            )

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(top = 10.dp)
        ) {
            Button(
                onClick = {
                    //多余，当enabled不可用时不会响应onClick
                    // if (count < 10) {
                    count++
                    //  }
                },
                enabled = count < 10,
            ) {
                Text(text = "Add One")
            }

            Button(
                onClick = {
                    count = 0
                },
                modifier = modifier.padding(start = 16.dp)
            ) {
                Text(text = "Clear Water")
            }
        }
    }
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {

    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text(text = "You've had $count glasses.")
        }

        Button(
            onClick = onIncrement,
            enabled = count < 10,
            modifier = modifier.padding(top = 10.dp)
        ) {
            Text(text = "Add One")
        }
    }
}


@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    //共享状态
    var count by remember {
        mutableIntStateOf(0)
    }

    var juiceCount by remember {
        mutableIntStateOf(0)
    }

    Column {
        StatelessCounter(juiceCount, { juiceCount++ }, modifier)

        StatelessCounter(count, { count++ }, modifier)

        StatelessCounter(count, { count *= 2 }, modifier)

    }

}
