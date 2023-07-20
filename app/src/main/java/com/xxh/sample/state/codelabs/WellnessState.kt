package com.xxh.sample.state.codelabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/**
 * 普通状态容器，封装界面逻辑和状态提升
 */
@Composable
fun rememberWellnessState(
    showState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    routeState: MutableState<String> = remember {
        mutableStateOf("main")
    }
) = WellnessState(showState, routeState)

//在当前场景中remember可省略
@Composable
fun rememberWellnessState2(
    showState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    routeState: MutableState<String> = remember {
        mutableStateOf("main")
    }
) = remember(showState, routeState) {
    WellnessState(showState, routeState)
}

@Stable
class WellnessState(
    var showState: MutableState<Boolean>,
    var routeState: MutableState<String>
) {
    @Composable
    fun handle(
        showMain: @Composable () -> Unit,
        showList1: @Composable () -> Unit,
        showList2: @Composable () -> Unit
    ) {
        if (!showState.value) {
            when (routeState.value) {
                "main" -> showMain()
                //点击切换时不会保存状态，因为数据赋值在列表的组合项内
                "list1" -> showList1()
            }
        } else {
            showList2()
        }
    }

    fun testChange() = if (showState.value) "隐藏" else "显示"

}