package com.xxh.sample.lifecycle

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xxh.sample.common.component.XInput

@Composable
fun ReCompositionTest() {

    var text by remember {
        mutableStateOf("aa")
    }

    var cardEle by remember {
        mutableIntStateOf(10)
    }

    /*XInput(text, onValueChange = { text = it }, doClick = {
        cardEle = text.toInt()
    })*/
   // Text(text)

    Row {
        Card(
            backgroundColor = MaterialTheme.colors.onError,
            modifier = Modifier
                .size(115.dp)
                .padding(4.dp)
        ) {
            Log.d("LocalElevationsSample", "CompositionLocalProvider1")
            Text("测试", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
        }


        Log.d("LocalElevationsSample", "CompositionLocalProvider2")
        Card(
            backgroundColor = MaterialTheme.colors.onError,
            modifier = Modifier
                .size(115.dp)
                .padding(4.dp)
        ) {
            Log.d("LocalElevationsSample", "CompositionLocalProvider3 text=$text")

            Log.d("LocalElevationsSample", "CompositionLocalProvider3")
            // Content
            Text(
                "测试",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red),
                textAlign = TextAlign.Center
            )

            Column(modifier = Modifier.padding(10.dp)) {
                Log.d("LocalElevationsSample", "CompositionLocalProvider4")
                Column {
                    // Content
                    Text("测2", textAlign = TextAlign.Center)
                    Text("测3", textAlign = TextAlign.Center)
                    Text("测4", textAlign = TextAlign.Center)
                    Log.d("LocalElevationsSample", "CompositionLocalProvider5")
                }

                Box {
                    Text("测5", textAlign = TextAlign.Center)
                    Log.d("LocalElevationsSample", "CompositionLocalProvider6")
                }
            }

        }

        Column {
            Text("测6", textAlign = TextAlign.Center)
            Log.d("LocalElevationsSample", "CompositionLocalProvider7")
        }

        Column {
            Text("测7", textAlign = TextAlign.Center)
            Log.d("LocalElevationsSample", "CompositionLocalProvider8")
            Row {
                Text("测8", textAlign = TextAlign.Center)
                Log.d("LocalElevationsSample", "CompositionLocalProvider9")
                SideEffect {
                    Log.d("LocalElevationsSample", "CompositionLocalProvider9 SideEffect")
                }
            }
        }

        Surface{
            Text("测9", textAlign = TextAlign.Center)
            Log.d("LocalElevationsSample", "CompositionLocalProvider10")
        }
    }

    Button(onClick = {
        text="bb"
    }){
        Text("change")
    }

    Column {
        var text2 by remember {
            mutableStateOf("aa")
        }
        Button(onClick = {
            text2="cc"
        }){
            Text("change2")
        }
        Text("测10", textAlign = TextAlign.Center)
        Log.d("LocalElevationsSample", "CompositionLocalProvider11")
        Log.d("LocalElevationsSample", "CompositionLocalProvider12 text=$text2")
    }
}
