package com.codelabs.state.test

val tasks
    get() = List(30) { index ->
        WellnessTask(index, "task # $index")
    }