package com.xxh.sample.statetest

object Data {
    fun getWellnessTasks() = List(30) {
        WellnessTask(it, "task # $it");
    }
}