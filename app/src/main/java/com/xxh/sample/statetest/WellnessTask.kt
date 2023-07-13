package com.xxh.sample.statetest

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WellnessTask(val id: Int, val label: String,val checked: MutableState<Boolean> = mutableStateOf(false))
//data class WellnessTask(val id: Int, val label: String,var checked: Boolean = false)

/*
class WellnessTask(
    val id: Int,
    val label: String,
    initialChecked: Boolean = false
) {
    var checked by mutableStateOf(initialChecked)
}*/
