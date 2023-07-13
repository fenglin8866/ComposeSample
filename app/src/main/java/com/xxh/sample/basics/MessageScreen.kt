package com.xxh.sample.basics

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xxh.sample.R
import com.xxh.sample.data.Message

@Composable
fun MessageCard(message: Message) {
    var isExpand by remember {
        mutableStateOf(false)
    }

    val color by animateColorAsState(
        if (isExpand) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )
    Surface(modifier = Modifier.padding(10.dp)) {

        Row {
            Image(
                painter = painterResource(id = R.drawable.avatar_3),
                contentDescription = "tp",
                modifier = Modifier
                    .size(50.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .border(
                        1.dp, MaterialTheme.colors.secondary, CircleShape
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(message.author)
                Divider()
                Surface(
                    color = color,
                    elevation = 1.dp,
                    modifier = Modifier
                        .clickable { isExpand = !isExpand }
                        .animateContentSize()
                ) {
                    Text(
                        text = message.body,
                        maxLines = if (!isExpand) 1 else Int.MAX_VALUE,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }
    }


}


@Composable
fun ListScreen(messages: List<Message>) {
    LazyColumn {
        items(messages) {
            MessageCard2( it)
        }
    }

}


@Composable
fun MessageCard2(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.avatar_3),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }


        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )


        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
                    .border(2.dp, MaterialTheme.colors.onSurface, CircleShape)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun Preview() {
    MessageCard(message = Message("aaaa", "xxxxxxxxxxxasdfasddadfasdfafasd1111"))
}

