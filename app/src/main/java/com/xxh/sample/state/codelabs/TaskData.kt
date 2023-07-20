package com.xxh.sample.state.codelabs

val tasks1
    get() = List(30) { index ->
        WellnessTask(index, "list1 task # $index")
    }

val tasks2
    get() = List(30) { index ->
        WellnessTask(index, "task # $index")
    }