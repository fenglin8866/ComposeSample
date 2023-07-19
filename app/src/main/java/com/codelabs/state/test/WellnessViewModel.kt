package com.codelabs.state.test

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WellnessViewModel : ViewModel() {

    private val _taskData = tasks.toMutableStateList()

    val taskData: List<WellnessTask> = _taskData

    fun onCloseTask(task: WellnessTask) {
        _taskData.remove(task)
    }

    fun checkedHint(task: WellnessTask, isCheck: Boolean) {
        viewModelScope.launch {
            Log.i("xxh", "${if (isCheck) "选择" else "取消"}了 ${task.label}")
        }
    }


}