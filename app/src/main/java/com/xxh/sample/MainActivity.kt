package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.main.ui.theme.SampleAppTheme
import com.xxh.sample.images.ImageTest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppTheme() {
               // ListScreen(messages = SampleData.conversationSample)
               // WellnessScreen(modifier = Modifier)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   // WellnessScreenB()
                   // AnimTest()
                    //HelloContent()
                   // TestLayout()
                    ImageTest()

                }
            }
        }
    }
}


@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {

        }) {
            Text(text = "跳转")
        }
        Text(
            text = "Hello!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Name") }
        )
    }
}


@Composable
fun HelloContent2() {
    Column(modifier = Modifier.padding(16.dp)) {
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
               // setName(it)
            },
            label = { Text("Name") }
        )
    }
}







