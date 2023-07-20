package com.xxh.sample.state.codelabs

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WellnessViewModel : ViewModel() {
    //状态只观察当前list中的元素增减，而不能观察元素的更改
    private val _taskData = tasks2.toMutableStateList()

    val taskData: List<WellnessTask> = _taskData

    fun onCloseTask(task: WellnessTask) {
        _taskData.remove(task)
    }

    fun checkedHint(task: WellnessTask, isCheck: Boolean) {
        viewModelScope.launch {
            Log.i("xxh", "${if (isCheck) "选择" else "取消"}了 ${task.label}")
        }
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        _taskData.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }

    fun changeTaskChange(task: WellnessTask, isChecked: Boolean) {
        task.checked = isChecked
    }


}