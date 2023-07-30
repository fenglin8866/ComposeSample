package com.xxh.sample.lifecycle

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LifecycleScreen() {
    Log.d("LifecycleScreen","LifecycleScreen0")
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
    }
}

@Composable
fun HelloContent3() {
    Log.d("LifecycleScreen","HelloContent3 0")
    Column(modifier = Modifier.padding(16.dp)) {
        Log.d("LifecycleScreen","HelloContent3 1")
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
                Log.d("LifecycleScreen","HelloContent3 OutlinedTextField1 1")
            },
            label = {
                Log.d("LifecycleScreen","HelloContent3 OutlinedTextField1 2")
                Text("Name") }
        )
        Log.d("LifecycleScreen","HelloContent3 2")

        Column {
            Text("aaaaaaaaaaaaaaa")
            Log.d("LifecycleScreen","HelloContent3 3")

            SideEffect {
                Log.d("LifecycleScreen","HelloContent3 Column SideEffect")
            }

        }

        SideEffect {
            Log.d("LifecycleScreen","HelloContent3 SideEffect")
        }
        LaunchedEffect(true){
            Log.d("LifecycleScreen","HelloContent3 LaunchedEffect")
        }
        DisposableEffect(true){
            Log.d("LifecycleScreen","HelloContent3 DisposableEffect")
            onDispose {
                Log.d("LifecycleScreen","HelloContent3 DisposableEffect onDispose")
            }
        }
    }
}

@Composable
fun HelloContent4() {
    Log.d("LifecycleScreen","HelloContent34 0")
    Column(modifier = Modifier.padding(16.dp)) {
        Log.d("LifecycleScreen","HelloContent34 1")
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
                Log.d("LifecycleScreen","HelloContent34 OutlinedTextField1 1")
            },
            label = {
                Log.d("LifecycleScreen","HelloContent34 OutlinedTextField1 2")
                Text("Name") }
        )
        Log.d("LifecycleScreen","HelloContent34 2")

        Column {
            Text("aaaaaaaaaaaaaaa")
            Log.d("LifecycleScreen","HelloContent34 3")
        }

        SideEffect {
            Log.d("LifecycleScreen","HelloContent34 SideEffect")
        }
        LaunchedEffect(true){
            Log.d("LifecycleScreen","HelloContent34 LaunchedEffect")
        }
        DisposableEffect(true){
            Log.d("LifecycleScreen","HelloContent34 DisposableEffect")
            onDispose {
                Log.d("LifecycleScreen","HelloContent34 DisposableEffect onDispose")
            }
        }
    }
}
