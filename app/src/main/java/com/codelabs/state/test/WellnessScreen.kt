package com.codelabs.state.test

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

        //控制切换主页和list1
        var route by remember {
            mutableStateOf("main")
        }

        //控制显示隐藏list2
        var showList by remember {
            mutableStateOf(false)
        }
        Row(modifier = modifier.padding(start = 16.dp)) {
            Button(
                onClick = { route = "main" },
                modifier = modifier
            ) {
                // Text(text = "${if (showList1) "隐藏" else "显示"}列表1")
                Text(text = "主页")
            }

            Button(
                onClick = { route = "list1" },
                modifier = modifier.padding(start = 10.dp)
            ) {
                // Text(text = "${if (showList1) "隐藏" else "显示"}列表1")
                Text(text = "列表1")
            }

            Button(
                onClick = { showList = !showList },
                modifier = modifier.padding(start = 10.dp)
            ) {
                Text(text = "${if (showList) "隐藏" else "显示"}列表2")
            }
        }

        if (!showList) {
            when (route) {
                "main" -> MainScreen(modifier)
                //点击切换时不会保存状态，因为数据赋值在列表的组合项内
                "list1" -> WellnessTaskList(modifier)
            }
        } else {
            if (tasks != null && onClose != null)
                WellnessTaskList(tasks, onCheckedChange, onClose, modifier)
        }
    }
}


/**
 * 状态提升，封装为状态容器
 */
@Composable
fun WellnessScreen2(
    wellnessViewModel: WellnessViewModel = viewModel(),
    wellnessState: WellnessState = rememberWellnessState(),
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row(modifier = modifier.padding(start = 16.dp)) {
            Button(
                onClick = { wellnessState.routeState.value = "main" },
                modifier = modifier
            ) {
                // Text(text = "${if (showList1) "隐藏" else "显示"}列表1")
                Text(text = "主页")
            }

            Button(
                onClick = { wellnessState.routeState.value = "list1" },
                modifier = modifier.padding(start = 10.dp)
            ) {
                // Text(text = "${if (showList1) "隐藏" else "显示"}列表1")
                Text(text = "列表1")
            }

            Button(
                onClick = { wellnessState.showState.value = !wellnessState.showState.value },
                modifier = modifier.padding(start = 10.dp)
            ) {
                Text(text = "${wellnessState.testChange()}列表2")
            }
        }

        wellnessState.handle(showMain = {
            MainScreen(modifier)
        }, showList1 = {
            //点击切换时不会保存状态，因为数据赋值在列表的组合项内
            WellnessTaskList(modifier)
        }) {
            WellnessTaskList(
                wellnessViewModel.taskData,
                wellnessViewModel::checkedHint,
                wellnessViewModel::onCloseTask,
                modifier
            )
        }
    }
}


/**
 *父类约束条件会传入子类约束。可以不定义布局
 *当提高可组合项的重用，建议添加在可组合项内添加容器，有时候不确定父类容器的约束
 */
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



