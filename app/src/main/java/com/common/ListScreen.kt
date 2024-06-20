package com.common

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.main.data.HomeData
import com.main.data.HomeMsg

/**
 * item使用HomeMsg类型的列表
 */
@Composable
fun ListScreenMsg(data: List<HomeMsg>, click: (String) -> Unit) {
    LazyColumn {
        items(data, key = { item -> item.id }) { item ->
            ListItem(item.title, item.content, click)
        }
    }
}

/**
 * item使用String类型的列表
 */
@Composable
fun ListScreenString(data: List<String>, click: (String) -> Unit) {
    LazyColumn {
        items(data) { title ->
            ListItem(title, click)
        }
    }
}

//todo 泛型的使用
@Composable
inline fun <reified T> ListScreenInline(
    data: List<T>,
    trans: (T) -> String,
    noinline click: (String) -> Unit
) {
    val itemData = if (T::class == String::class) data else data.map { trans(it) }
    LazyColumn {
        items(itemData) {
            ListItem(it as String, click)
        }
    }
}

/**
 * item使用通用类型的列表
 */
@Composable
fun <T> ListScreen(
    data: List<T>,
    trans: (T) -> String,
    click: (String) -> Unit
) {
    val itemData = data.map { trans(it) }
    LazyColumn {
        items(itemData) {
            ListItem(it, click)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(title: String, content: String, click: (String) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .wrapContentSize(),
        onClick = {
            click(content)
        }
    ) {
        Text(
            text = title, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 10.dp)
                .wrapContentSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(title: String, click: (String) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .wrapContentSize(),
        onClick = {
            click(title)
        }
    ) {
        Text(
            text = title, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(vertical = 10.dp)
                .wrapContentSize()
        )
    }
}
@Preview
@Composable
fun test(){
    ListScreenMsg(data = HomeData.codelabsData) {
        //clickCodelabs(it, context)
    }
}
