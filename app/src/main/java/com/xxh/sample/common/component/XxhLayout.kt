package com.xxh.sample.common.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun XDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier
            .height(6.dp)
            .fillMaxWidth()
    )
}

@Composable
fun XInput(
    value: String,
    onValueChange: (String) -> Unit,
    doClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(2.dp)
    ) {
        TextField(value = value, onValueChange = onValueChange, modifier = modifier.weight(1.0F))
        Button(onClick = doClick, modifier = modifier.padding(10.dp)) {
            Text(text = "点击")
        }
    }
}

@Composable
fun XInput(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(2.dp)
    ) {
        var text by remember {
            mutableStateOf("")
        }
        TextField(value = text, onValueChange = { text = it }, modifier = modifier.weight(1.0F))
        Button(onClick = { text = "hint" }, modifier = modifier.padding(10.dp)) {
            Text(text = "点击")
        }
    }
}