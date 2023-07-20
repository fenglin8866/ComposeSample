package com.codelabs.state.test

val tasks1
    get() = List(30) { index ->
        WellnessTask(index, "list1 task # $index")
    }

val tasks
    get() = List(30) { index ->
        WellnessTask(index, "task # $index")
    }