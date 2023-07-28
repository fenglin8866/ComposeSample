package com.xxh.sample.others.compositionlocal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * CompositionLocal的替代方法
 * 1.传递显示参数
 * 2.控制反转
 */

@Composable
fun CompositionLocalReplace() {
}

@Composable
fun MyDescendant(data: String) {
    // Display data
}

class MyViewModel : ViewModel() {
    fun loadData() {

    }
}

@Composable
fun MyComposable(myViewModel: MyViewModel = viewModel()) {
    // ...
    ReusableLoadDataButton(
        onLoadClick = {
            myViewModel.loadData()
        }
    )
    ReusablePartOfTheScreen(
        content = {
            Button(
                onClick = {
                    myViewModel.loadData()
                }
            ) {
                Text("Confirm")
            }
        }
    )
}

/**
 * 提升事件
 */
@Composable
fun ReusableLoadDataButton(onLoadClick: () -> Unit) {
    Button(onClick = onLoadClick) {
        Text("Load data")
    }
}


/**
 * 提升可组合项，内部使用事件，更加的灵活
 */
@Composable
fun ReusablePartOfTheScreen(content: @Composable () -> Unit) {
    Column {
        // ...
        content()
    }
}