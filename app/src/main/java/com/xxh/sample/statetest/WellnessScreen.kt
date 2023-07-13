package com.xxh.sample.statetest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter()

        WellnessTasksList(
            list = wellnessViewModel.tasks,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task ->
                wellnessViewModel.remove(task)
            }
        )
    }
}

@Composable
fun WellnessScreen1(modifier: Modifier = Modifier) {
    // WaterCounter(modifier = modifier)
    Column {
        StatefulCounter()
        val list = remember {
            Data.getWellnessTasks().toMutableStateList()
        }
        WellnessTasksList(list = list) {
            list.remove(it)
        }
    }
}

@Composable
fun WaterCounter(modifier: Modifier) {
    //var count = 0
    var count by remember {
        mutableStateOf(0)
    }
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(
            onClick = { count++ },
            enabled = count < 10,
            modifier = modifier.padding(top = 6.dp)
        ) {
            Text(text = "Add One")
        }
    }
}


@Composable
fun StatelessCounter(count: Int, onIncClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(
            onClick = onIncClick,
            enabled = count < 10,
            modifier = modifier.padding(top = 6.dp)
        ) {
            Text(text = "Add One")
        }
    }
}

@Composable
fun StatefulCounter() {
    var count by remember {
        mutableStateOf(0)
    }
    StatelessCounter(count = count, onIncClick = { count++ })

    StatelessCounter(count = count, onIncClick = { count *= 2 })
}

@Composable
fun WaterCounter2(modifier: Modifier) {
    //var count = 0
    var count by remember {
        mutableStateOf(0)
    }
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            var showTask by remember {
                mutableStateOf(true)
            }
            if (showTask) {
                WellnessTaskItem2(task = "Have you taken your 15 minute walk today?",
                    onClose = { showTask = false })
            }
            Text("You've had $count glasses.")
        }
        Row(modifier = modifier.padding(top = 6.dp)) {
            Button(
                onClick = { count++ },
                enabled = count < 10
            ) {
                Text(text = "Add One")
            }
            Button(
                onClick = { count = 0 },
                modifier = modifier.padding(start = 6.dp),
            ) {
                Text("Clear water count")
            }
        }

    }
}

@Composable
fun WellnessTaskItem2(
    task: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = task, modifier = modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}


@Composable
fun WellnessTaskItem(modifier: Modifier = Modifier, task: String, onClose: () -> Unit) {
    var checkedState by rememberSaveable {
        mutableStateOf(false)
    }
    WellnessTaskItem(
        task = task,
        checked = checkedState,
        onCheckedChange = { checkedState = !checkedState },
        onClose = onClose
    )
}

@Composable
fun WellnessTaskItem(task: String, modifier: Modifier = Modifier) {
    var checkedState by rememberSaveable {
        mutableStateOf(false)
    }
    WellnessTaskItem(
        task = task,
        checked = checkedState,
        onCheckedChange = { checkedState = !checkedState },
        onClose = { })
}

@Composable
fun WellnessTaskItem(
    task: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = task, modifier = modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}
