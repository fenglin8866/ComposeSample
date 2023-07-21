package com.xxh.sample.state.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        /**
         * 构建状态的三种方式
         * var name by remember { mutableStateOf("") }
         * 常量不能设置值
         * val name = remember { mutableStateOf("") }
         * 解构数据，setName( newValue )
         * val (name, setName) = remember { mutableStateOf("") }
         */
        val (name, setName) = remember { mutableStateOf("") }
        if (name.isNotEmpty()) {
            Text(
                text = "Hello , $name !",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = name,
            onValueChange = {
                setName(it)
            },
            label = { Text("Name") }
        )
    }
}

/*====================================状态提升============================================*/

@Composable
fun HelloScreen() {
    var name by remember { mutableStateOf("") }
    HelloContent(name) { newValue ->
        name = newValue
    }
}

@Composable
fun HelloContent(value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (value.isNotEmpty()) {
            Text(
                text = "Hello , $value !",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Name") }
        )
    }
}