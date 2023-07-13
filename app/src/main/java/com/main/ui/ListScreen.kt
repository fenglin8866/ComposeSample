package com.main.ui

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
import androidx.compose.ui.unit.dp
import com.main.data.Message


@Composable
fun ListScreen(data: List<Message>, click: (String) -> Unit) {
    LazyColumn {
        items(data, key = { item -> item.id }) { item ->
            ListItem(item.title, item.content, click)
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