package com.xxh.sample.state.codelabs

object Data {
    fun getWellnessTasks() = List(30) {
        WellnessTask(it, "task # $it");
    }
}