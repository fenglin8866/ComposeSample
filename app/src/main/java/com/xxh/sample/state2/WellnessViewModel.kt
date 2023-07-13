package com.xxh.sample.state2

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {
    private var _tasks: MutableList<WellnessTask> = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask> = _tasks
    fun remove(task: WellnessTask) {
        _tasks.remove(task)
    }

    fun changeTaskChange(task: WellnessTask, isChecked: Boolean) {
        task.isChecked.value = isChecked
    }
}

fun getWellnessTasks() = List(30) {
    WellnessTask(it, "Task # $it")
}