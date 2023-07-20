package com.codelabs.state.test

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Text(
            text = taskName, modifier = modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = null)
        }
    }
}

@Composable
fun WellnessTaskItem(
    //taskName: String,
    task: WellnessTask,
    onCheckedChange: ((WellnessTask, Boolean) -> Unit)? = null,
    onClose: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier
) {
    /**
     *  滑动列表，导致item不可见，移除组合，导致状态初始化
     *  1.rememberSaveable
     *  2.状态提升组件外
     *
     *  问题点：选择位置删除导致check状态保存不变，保存的是节点不变导致
     *  解决：1.rememberSaveable添加key
     *       2.items添加key
     */

    /* var checkState by rememberSaveable(task.label) {
         mutableStateOf(false)
     }*/
    WellnessTaskItem(
        task.label,
        task.checked,
        { newValue ->
            task.checked = newValue
            if (onCheckedChange != null) {
                onCheckedChange(task, newValue)
            }
        },
        { onClose(task) },
        modifier
    )
}