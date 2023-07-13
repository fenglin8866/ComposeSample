package com.xxh.sample.state.codelabs

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WellnessTask(
    val id: Int,
    val label: String,
    val isChecked: MutableState<Boolean> = mutableStateOf(false)
)

/*
 checked如果没有状态，点击不会重组，导致页面没有变化
 data class WellnessTask(val id: Int, val label: String,var checked: Boolean = false)
 */

/*
与第一种定义相同
data class WellnessTask(
    val id: Int,
    val label: String,
    initialChecked: Boolean = false
) {
    var checked by mutableStateOf(initialChecked)
}*/
