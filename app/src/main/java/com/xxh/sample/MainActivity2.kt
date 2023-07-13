package com.xxh.sample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.example.composeapplication.MainActivity
import com.main.ui.theme.SampleAppTheme

class MainActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxWidth( ),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // Greeting("Android")
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen(){
    val context = LocalContext.current
    Button(onClick = {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(context, intent, null)
     }) {
        Text(text = "跳转")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
