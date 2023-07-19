package com.codelabs.state.test

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellnessScreen(
    wellnessViewModel: WellnessViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    WellnessScreen(
        wellnessViewModel.taskData,
        wellnessViewModel::checkedHint,
        wellnessViewModel::onCloseTask,
        modifier
    )
}

@Composable
fun WellnessScreen(
    tasks: List<WellnessTask>? = null,
    onCheckedChange: ((WellnessTask, Boolean) -> Unit)? = null,
    onClose: ((WellnessTask) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        var showList1 by remember {
            mutableStateOf(false)
        }
        var showList2 by remember {
            mutableStateOf(false)
        }
        Row(modifier = modifier.padding(start = 16.dp)) {
            Button(
                onClick = { showList1 = !showList1 },
                modifier = modifier
            ) {
                Text(text = "${if (showList1) "隐藏" else "显示"} +列表1")
            }

            Button(
                onClick = { showList2 = !showList2 },
                modifier = modifier.padding(start = 10.dp)
            ) {
                Text(text = "${if (showList2) "隐藏" else "显示"} +列表2")
            }
        }

        if (showList2) {
            if (tasks != null && onClose != null)
                WellnessTaskList(tasks, onCheckedChange, onClose, modifier)
        }else{
            MainScreen(modifier)
        }

       /* if (showList1) {
            WellnessTaskList(modifier)
        }*/

    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column {
        Divider(
            modifier = modifier
                .height(6.dp)
                .fillMaxWidth()
        )
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



