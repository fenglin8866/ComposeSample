package com.xxh.sample.state.blog

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xxh.sample.R
import com.xxh.sample.common.data.Message
import com.xxh.sample.common.data.SampleData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/*@Composable
private fun ConversationScreen(
    conversationViewModel: ConversationViewModel = viewModel()
) {

    val messages by conversationViewModel.messages.collectAsStateWithLifecycle()

    ConversationScreen(messages, { message -> conversationViewModel.sendMessage(message) })

}*/

// in Compose

/*@Composable
private fun ConversationScreen(
    conversationViewModel = viewModel()
) {

    val scope = rememberCoroutineScope()

    ConversationScreen(onCloseDrawer = { viewModel.closeDrawer(uiScope = scope) })

}*/


/*@Composable
private fun ConversationScreen(
    messages: List<Messages>, onSendMessage: (Message) -> Unit)
) {

    MessagesList(messages, onSendMessage)
    // ...
}*/



@Composable
fun ConversationScreen() {
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState() // State hoisted to the ConversationScreen
    Column {
        MessagesList(SampleData.conversationSample, lazyListState,scope) // Reuse same state in MessageList

        /*UserInput(
            onMessageSent = { // Apply UI logic to lazyListState
                scope.launch {
                    lazyListState.scrollToItem(0)
                }
            },
        )*/
    }

}

@Composable
private fun UserInput(onMessageSent: () -> Unit) {
    var name by remember {
        mutableStateOf("")
    }
    TextField(value = name, onValueChange = { name = it })
}

@Composable
private fun MessagesList(
    messages: List<Message>,
    lazyListState: LazyListState = rememberLazyListState(),// LazyListState has a default value
    scope: CoroutineScope
) {
    Column(Modifier.fillMaxSize()){
        LazyColumn(
            state = lazyListState // Pass hoisted state to LazyColumn
        ) {
            items(messages) { item ->
                MessageCard3(item)
            }
        }

        JumpToBottom(onClicked = {
            scope.launch {
                lazyListState.scrollToItem(messages.size-1) // UI logic being applied to lazyListState
            }
        })
    }
}

@Composable
private fun JumpToBottom(onClicked:()->Unit){
    Button(onClick = onClicked, modifier = Modifier.wrapContentSize()) {
        Text(text = "点击滚动到底部")
    }
}

@Composable
fun MessageCard3(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.avatar_3),
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
