package com.xxh.sample.state2

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WellnessTask(
    val id: Int,
    val label: String,
    val isChecked: MutableState<Boolean> = mutableStateOf(false)
)