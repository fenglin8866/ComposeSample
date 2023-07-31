package com.xxh.sample.lifecycle

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xxh.sample.common.component.XInput

@Composable
fun LifecycleScreen() {

    var text by remember {
        mutableStateOf("")
    }

    var cardEle by remember {
        mutableIntStateOf(10)
    }
    Column {
        XInput(text, onValueChange = { text = it }, doClick = {
            cardEle = text.toInt()
        })

        Column {
            Text(text = "testX")
            Log.d("LifecycleScreen","LifecycleScreenX")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "button")
                Log.d("LifecycleScreen","LifecycleScreenX Button card=$cardEle")
            }
        }
        Log.d("LifecycleScreen","LifecycleScreenY")
    }


    /* Column {
         Log.d("LifecycleScreen", "LifecycleScreen0")
         HelloContentA()
         HelloContentB()
     }*/

    /* Log.d("LifecycleScreen","LifecycleScreen0")
     Column {
         Log.d("LifecycleScreen","LifecycleScreen1")
         HelloContent3()
         Log.d("LifecycleScreen","LifecycleScreen2")
         HelloContent4()
         Log.d("LifecycleScreen","LifecycleScreen3")

         SideEffect {
             Log.d("LifecycleScreen","LifecycleScreen SideEffect")
         }
         LaunchedEffect(true){
             Log.d("LifecycleScreen","LifecycleScreen LaunchedEffect")
         }
         DisposableEffect(true){
             Log.d("LifecycleScreen","LifecycleScreen DisposableEffect")
             onDispose {
                 Log.d("LifecycleScreen","LifecycleScreen DisposableEffect onDispose")
             }
         }
     }*/
}


/**
 * 11:53:42.003  D  HelloContentA name
 * 11:53:42.007  D  HelloContentA onClick
 * 11:53:42.009  D  HelloContentA onClickCC
 * 11:53:42.009  D  HelloContentA name1
 * 11:53:42.010  D  HelloContentA name2=AA
 *
 *
 * 11:53:48.866  D  HelloContentA name
 * 11:53:48.867  D  HelloContentA name1
 * 11:53:48.867  D  HelloContentA name2=change
 */
@Composable
fun HelloContentA() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("AA") }
        Log.d("LifecycleScreen", "HelloContentA name")
        Text(text = "测试1")
        Row {
            Text(text = "测试1")

            Button(onClick = { name = "change" }) {
                Text(text = "BB")
                Log.d("LifecycleScreen", "HelloContentA onClick")
            }

            Button(onClick = {
//                Log.d("LifecycleScreen","HelloContentA onClick2 name2=$name")
            }) {
                Text(text = "CC")
                Log.d("LifecycleScreen", "HelloContentA onClickCC")
            }
            Column {
                Text(text = "测试1")
                Log.d("LifecycleScreen", "HelloContentA name1")
            }
            Log.d("LifecycleScreen", "HelloContentA name2=$name")

            XChange{
                Log.d("LifecycleScreen", "HelloContentA XChange name=")
            }
        }
    }
}

@Composable
fun XChange(change: () -> Unit) {
    Text(text = "XChange")
    change()
}

/**
 *   Log.d("LifecycleScreen", "HelloContentA onClickCC")
 *   Log.d("LifecycleScreen", "HelloContentA onClick2 name2=$name")
 */
@Composable
fun HelloContentB() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember { mutableStateOf("AA") }
        Log.d("LifecycleScreen", "HelloContentA name")
        Text(text = "测试1")
        Row {
            Text(text = "测试1")

            Button(onClick = { name = "change" }) {
                Text(text = "BB")
                Log.d("LifecycleScreen", "HelloContentA onClick")
            }

            Button(onClick = {
            }) {
//                Text(text = name)
                Log.d("LifecycleScreen", "HelloContentA onClickCC")
                Log.d("LifecycleScreen", "HelloContentA onClick2 name2=$name")
            }
            Column {
                Text(text = "测试1")
                Log.d("LifecycleScreen", "HelloContentA name1")
            }
//            Log.d("LifecycleScreen","HelloContentA name2=$name")
        }
    }
}


/**
 * 11:05:42.599  D  HelloContent34 OutlinedTextField1 1
 * 11:05:42.613  D  HelloContent34 0
 * 11:05:42.613  D  HelloContent34 1
 * 11:05:42.626  D  HelloContent34 2
 * 11:05:42.626  D  HelloContent34 3
 * 11:05:42.630  D  HelloContent34 Column SideEffect
 * 11:05:42.630  D  HelloContent34 SideEffect
 */
@Composable
fun HelloContent3() {
    Log.d("LifecycleScreen", "HelloContent3 0")
    Column(modifier = Modifier.padding(16.dp)) {
        Log.d("LifecycleScreen", "HelloContent3 1")
        var name by remember { mutableStateOf("") }

        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                Log.d("LifecycleScreen", "HelloContent3 OutlinedTextField1 1")
            },
            label = {
                Log.d("LifecycleScreen", "HelloContent3 OutlinedTextField1 2")
                Text("Name")
            }
        )
        Log.d("LifecycleScreen", "HelloContent3 2")

        Column {
            Text("aaaaaaaaaaaaaaa")
            Log.d("LifecycleScreen", "HelloContent3 3")
            SideEffect {
                Log.d("LifecycleScreen", "HelloContent3 Column SideEffect")
            }
        }

        SideEffect {
            Log.d("LifecycleScreen", "HelloContent3 SideEffect")
        }
        LaunchedEffect(true) {
            Log.d("LifecycleScreen", "HelloContent3 LaunchedEffect")
        }
        DisposableEffect(true) {
            Log.d("LifecycleScreen", "HelloContent3 DisposableEffect")
            onDispose {
                Log.d("LifecycleScreen", "HelloContent3 DisposableEffect onDispose")
            }
        }
    }
}

@Composable
fun HelloContent4() {
    Log.d("LifecycleScreen", "HelloContent34 0")
    Column(modifier = Modifier.padding(16.dp)) {
        Log.d("LifecycleScreen", "HelloContent34 1")
        var name by remember { mutableStateOf("") }

        if (name.isNotEmpty()) {
            Text(
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                Log.d("LifecycleScreen", "HelloContent34 OutlinedTextField1 1")
            },
            label = {
                Log.d("LifecycleScreen", "HelloContent34 OutlinedTextField1 2")
                Text("Name")
            }
        )
        Log.d("LifecycleScreen", "HelloContent34 2")

        Column {
            Text("aaaaaaaaaaaaaaa")
            Log.d("LifecycleScreen", "HelloContent34 3")
            SideEffect {
                Log.d("LifecycleScreen", "HelloContent34 Column SideEffect")
            }
        }

        SideEffect {
            Log.d("LifecycleScreen", "HelloContent34 SideEffect")
        }
        LaunchedEffect(true) {
            Log.d("LifecycleScreen", "HelloContent34 LaunchedEffect")
        }
        DisposableEffect(true) {
            Log.d("LifecycleScreen", "HelloContent34 DisposableEffect")
            onDispose {
                Log.d("LifecycleScreen", "HelloContent34 DisposableEffect onDispose")
            }
        }
    }
}

/*

11:01:46.438  D  LifecycleScreen0
11:01:46.438  D  LifecycleScreen1
    11:01:46.438  D  HelloContent3 0
    11:01:46.438  D  HelloContent3 1
    11:01:46.455  D  HelloContent3 OutlinedTextField1 2
    11:01:46.455  D  HelloContent3 2
    11:01:46.456  D  HelloContent3 3
11:01:46.456  D  LifecycleScreen2
    11:01:46.456  D  HelloContent34 0
    11:01:46.456  D  HelloContent34 1
    11:01:46.464  D  HelloContent34 OutlinedTextField1 2
    11:01:46.464  D  HelloContent34 2
    11:01:46.464  D  HelloContent34 3
11:01:46.465  D  LifecycleScreen3
    11:01:46.481  D  HelloContent3 DisposableEffect
    11:01:46.481  D  HelloContent34 DisposableEffect
11:01:46.481  D  LifecycleScreen DisposableEffect
    11:01:46.481  D  HelloContent3 Column SideEffect
    11:01:46.481  D  HelloContent3 SideEffect
    11:01:46.482  D  HelloContent34 Column SideEffect
    11:01:46.482  D  HelloContent34 SideEffect
11:01:46.482  D  LifecycleScreen SideEffect
    11:01:46.501  D  HelloContent3 LaunchedEffect
    11:01:46.501  D  HelloContent34 LaunchedEffect
11:01:46.501  D  LifecycleScreen LaunchedEffect


=======================================================================
11:04:34.309  D  HelloContent3 OutlinedTextField1 1
11:04:34.316  D  HelloContent3 0
11:04:34.317  D  HelloContent3 1
11:04:34.340  D  HelloContent3 2
11:04:34.341  D  HelloContent3 3
11:04:34.348  D  HelloContent3 Column SideEffect
11:04:34.349  D  HelloContent3 SideEffect

11:05:18.426  D  HelloContent3 OutlinedTextField1 1
11:05:18.436  D  HelloContent3 0
11:05:18.437  D  HelloContent3 1
11:05:18.448  D  HelloContent3 2
11:05:18.448  D  HelloContent3 3
11:05:18.451  D  HelloContent3 Column SideEffect
11:05:18.451  D  HelloContent3 SideEffect

================================
11:05:42.599  D  HelloContent34 OutlinedTextField1 1
11:05:42.613  D  HelloContent34 0
11:05:42.613  D  HelloContent34 1
11:05:42.626  D  HelloContent34 2
11:05:42.626  D  HelloContent34 3
11:05:42.630  D  HelloContent34 Column SideEffect
11:05:42.630  D  HelloContent34 SideEffect

11:05:42.797  D  HelloContent34 OutlinedTextField1 1
11:05:42.811  D  HelloContent34 0
11:05:42.811  D  HelloContent34 1
11:05:42.819  D  HelloContent34 2
11:05:42.820  D  HelloContent34 3
11:05:42.823  D  HelloContent34 Column SideEffect
11:05:42.823  D  HelloContent34 SideEffect

===============================

 */
