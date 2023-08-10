package com.xxh.sample.basics.lifecycle

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.xxh.sample.common.component.XInput

@Composable
fun LifecycleScreen() {

}

@Composable
fun LoginScreen(showError: Boolean) {
    var text by remember {
        mutableStateOf("请输入手机号")
    }
    var showError by remember {
        mutableStateOf(false)
    }
    Column {
        if (showError) {
            LoginError()
        }
       // LoginInput() // This call site affects where LoginInput is placed in Composition
        XInput(text, onValueChange = { text = it }, doClick = {
            try{
                text.toInt()
                showError=false
            }catch (e:Exception){
                showError=true
            }
        })
    }
}

@Composable
fun LoginError() {
    Text("输入错误")
}



@Composable
fun MoviesScreen(movies: List<Movie>) {
    Column {
        for (movie in movies) {
            key(movie.id) { // Unique ID for this movie
                MovieOverview(movie)
            }
        }
    }

    //一些可组合项提供对 key 可组合项的内置支持。
    LazyColumn {
        items(movies, key = { movie -> movie.id }) { movie ->
            MovieOverview(movie)
        }
    }
}

@Composable
fun MovieOverview(movie: Movie) {
    Column {
        // 副作用将在文档后面解释。如果 MovieOverview 在获取图像的过程中进行重组，则会取消并重新启动。
        // Side effect explained later in the docs. If MovieOverview
        // recomposes, while fetching the image is in progress,
        // it is cancelled and restarted.
        val image = loadNetworkImage(movie.url)
        MovieHeader(image)

        /* ... */
    }
}

@Composable
fun MovieHeader(image: Bitmap) {
    TODO("Not yet implemented")
}

fun loadNetworkImage(url: String): Bitmap {
    TODO("Not yet implemented")
}

data class Movie(val id:Int,val name:String,val url:String)

