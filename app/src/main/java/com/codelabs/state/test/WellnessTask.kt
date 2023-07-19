package com.codelabs.state.test

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class WellnessTask(
    val id: Int, val label: String, initialChecked: Boolean = false
) {
    //响应界面状态，不需在ViewModel添加该界面逻辑，如果有业务逻辑，需要提取到ViewModel处理
    var checked by mutableStateOf(initialChecked)
}

