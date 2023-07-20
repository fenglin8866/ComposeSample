package com.xxh.sample.state.codelabs

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/*
 checked如果没有状态，点击不会重组，导致页面没有变化
 data class WellnessTask(val id: Int, val label: String,var checked: Boolean = false)
 */

class WellnessTask(
    val id: Int, val label: String, initialChecked: Boolean = false
) {
    //响应界面状态，不需在ViewModel添加该界面逻辑，如果有业务逻辑，需要提取到ViewModel处理
    var checked by mutableStateOf(initialChecked)
}

/*
与第一种定义相同
data class WellnessTask(
    val id: Int,
    val label: String,
    val isChecked: MutableState<Boolean> = mutableStateOf(false)
)
*/


