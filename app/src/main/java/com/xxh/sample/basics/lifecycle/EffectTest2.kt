package com.xxh.sample.basics.lifecycle

import android.media.Image
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.common.XLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * 1.LaunchedEffect:在可组项内运行挂起函数
 * 2.SideEffect：将Compose中state发布到非Compose代码
 * 3.DisposableEffect：清理效应
 * 4.rememberCoroutineScope:获取组合感知作用域，以便在组合作用域之外启动协程
 * 5.rememberUpdateState：在效应中引用某值，在值发生改变时，效应不重启
 */

@Composable
fun EffectTest2() {
    Column(modifier = Modifier.padding(10.dp)) {
        EffectScreen()
    }
}

@Composable
fun EffectScreen(scaffoldState: ScaffoldState = rememberScaffoldState()) {
    var number by remember {
        mutableIntStateOf(0)
    }
    /*var onStart by remember {
        rememberUpdatedState(newValue = )
    }
*/
    var isChecked by remember {
        mutableStateOf(false)
    }

    Column {
        Text(text = "Text $number")
        Button(onClick = { number++ }) {
            Text(text = "add one")
        }
        Button(onClick = {
            isChecked = !isChecked
        }) {
            Text(text = "显示SnackBar")
        }
        LaunchedEffect(isChecked) {
            XLog.d("Effect", "LaunchedEffect")
        }
        SideEffect {
            XLog.d("Effect", "SideEffect")
        }
        DisposableEffect(key1 = isChecked) {
            XLog.d("Effect", "DisposableEffect")
            onDispose {
                XLog.d("Effect", "onDispose")
            }
        }
    }
}
/*
17:30:38.217 SampleDemo               D  [Effect] DisposableEffect
17:30:38.217 SampleDemo               D  [Effect] SideEffect
17:30:38.236 SampleDemo               D  [Effect] LaunchedEffect

17:30:40.059 SampleDemo               D  [Effect] SideEffect
17:30:41.632 SampleDemo               D  [Effect] SideEffect

17:30:42.833 SampleDemo               D  [Effect] onDispose
17:30:42.834 SampleDemo               D  [Effect] DisposableEffect
17:30:42.834 SampleDemo               D  [Effect] SideEffect
17:30:42.839 SampleDemo               D  [Effect] LaunchedEffect

17:30:45.655 SampleDemo               D  [Effect] onDispose
 */




