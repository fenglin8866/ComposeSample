package com.xxh.sample.statetest

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.xxh.sample.statetest.Data
import com.xxh.sample.statetest.WellnessTask
import com.xxh.sample.statetest.WellnessTaskItem

@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                task = task.label,
                checked = task.checked.value,
                onCheckedChange = { onCheckedTask(task, it) },
                onClose = { onCloseTask(task) }
            )
        }
    }
}

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>,
    onCloseTask: (WellnessTask) -> Unit
) {
    LazyColumn(modifier) {
        items(
            items = list,
            key = { task -> task.id }
        ) {
            WellnessTaskItem(task = it.label, onClose = { onCloseTask(it) })
        }
    }
}

@Composable
fun WellnessTasksList2(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = remember {
        Data.getWellnessTasks()
    }
) {
    LazyColumn(modifier) {
        items(list) {
            WellnessTaskItem(task = it.label, modifier)
        }
    }
}