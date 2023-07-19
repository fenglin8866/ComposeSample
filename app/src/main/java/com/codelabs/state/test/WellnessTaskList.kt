package com.codelabs.state.test

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier


val tasks
    get() = List(30) { index ->
        WellnessTask(index, "task # $index")
    }


@Composable
fun WellnessTaskList(modifier: Modifier = Modifier) {
    val taskDataState = tasks.toMutableStateList()
    LazyColumn(modifier) {
        items(taskDataState,key={it.id}) { task ->
            WellnessTaskItem(task, {
                 taskDataState.remove(it)
            }, modifier)

        }
    }

}