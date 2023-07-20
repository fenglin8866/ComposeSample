package com.xxh.sample.state.codelabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 * 列表1，默认状态处理。用于测试，预览
 */
@Composable
fun WellnessTaskList(modifier: Modifier = Modifier) {
    val taskDataState = tasks1.toMutableStateList()
    WellnessTaskList(tasks = taskDataState, onClose = { taskDataState.remove(it) })
}

@Composable
fun WellnessTaskList(
    tasks: List<WellnessTask>,
    onCheckedChange: ((WellnessTask, Boolean) -> Unit)? = null,
    onClose: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Divider(
            modifier = modifier
                .height(6.dp)
                .fillMaxWidth()
        )
        LazyColumn(modifier) {
            items(tasks, key = { it.id }) { task ->
                WellnessTaskItem(task, onCheckedChange, onClose, modifier)
            }
        }
    }
}