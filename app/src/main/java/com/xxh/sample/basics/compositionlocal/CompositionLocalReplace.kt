package com.xxh.sample.basics.compositionlocal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * CompositionLocal的替代方法
 * 1.传递显示参数
 *    a、不要传递整个数据对象，仅传递所需的参数
 *    b、不要使用CompositionLocal封装ViewModel
 * 2.控制反转
 */


// Don't pass the whole object! Just what the descendant needs.
// Also, don't  pass the ViewModel as an implicit dependency using
// a CompositionLocal.
/**
 * 错误范例
 */
@Composable
fun MyDescendant(myViewModel: MyViewModel) {

}

/**
 * 错误范例
 * Pass only what the descendant needs
 */
@Composable
fun MyDescendant(data: DataToDisplay) {
    // Display data
}

/**
 * 仅传递必须的参数
 */
@Composable
fun MyDescendant(title: String) {
    // Display data
}

@Composable
fun CompositionLocalReplace(myViewModel: MyViewModel = viewModel()) {
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

//=============================================================================
class MyViewModel : ViewModel() {
    fun loadData() {

    }
}

data class DataToDisplay(val title:String)

