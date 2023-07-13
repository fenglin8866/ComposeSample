package com.xxh.sample.state2

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreenB(viewModel: WellnessViewModel = viewModel()) {
    WellnessList(tasks = viewModel.tasks,
        onClick = { viewModel.remove(it) },
        onTaskStateChange = { task, isChecked ->
            viewModel.changeTaskChange(task, isChecked)
        }
    )

}

@Composable
fun WellnessList(
    tasks: List<WellnessTask>,
    onClick: (WellnessTask) -> Unit,
    onTaskStateChange: (WellnessTask, Boolean) -> Unit
) {
    LazyColumn {
        items(
            tasks,
            key = { task -> task.id }
        ) {
            WellnessItem(taskName = it.label,
                checked = it.isChecked.value,
                onClick = { onClick(it) }) { isChecked ->
                onTaskStateChange(it, isChecked)
            }
        }
    }

}

@Composable
fun WellnessItem(
    taskName: String,
    checked: Boolean,
    onClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        Text(text = taskName, Modifier.weight(1f))
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(onClick = onClick) {
            Icon(Icons.Filled.Close, contentDescription = "close")
        }
    }
}
