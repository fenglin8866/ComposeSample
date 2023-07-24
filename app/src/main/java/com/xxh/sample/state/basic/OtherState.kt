package com.xxh.sample.state.basic

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember

/**
 * 验证重组的点，当状态发生变化时所有涉及状态的引用包含创建的可组合项都进入重组
 * 初始化：
 * I/xxh11: StateBasicScreen
 * I/xxh11: count0
 * I/xxh11: count2
 * I/xxh11: count3
 * I/xxh11: count5
 * I/xxh11: count4
 * 点击add按钮
 * I/xxh11: count1=1
 * I/xxh11: count0
 * I/xxh11: count2
 * I/xxh11: count5
 *
 *
 */
@Composable
fun OtherState() {
    Log.i("xxh11", "count0")
    //var count = mutableIntStateOf(0)
    var count = remember {
        mutableIntStateOf(0)
    }

    OtherState(count.value) {
        count.value++
        Log.i("xxh11", "count1=${count.value}")
    }
}

@Composable
fun OtherState(count: Int, onClick: () -> Unit) {
    Column {
        Log.i("xxh11", "count2")
        Text(text = "数字变化${count}")
        Button(onClick = onClick) {
            Log.i("xxh11", "count3")
            Text(text = "add")
        }
        Log.i("xxh11", "count5")
        Button(onClick = {}) {
            Log.i("xxh11", "count4")
            Text(text = "show")
        }
    }
}