package com.xxh.sample.state.codelabs

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {

    //状态只观察当前list中的元素增减，而不能观察元素的更改
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        tasks.find { it.id == item.id }?.let { task ->
            task.isChecked.value = checked
        }

    fun changeTaskChange(task: WellnessTask, isChecked: Boolean) {
        task.isChecked.value = isChecked
    }
}

private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }