package com.xxh.sample.state2.test1

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xxh.sample.SampleData
import com.xxh.sample.Message
import com.xxh.sample.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class StateTest {
}
@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by remember {
            mutableStateOf("")
        }
        //val mutableState = remember { mutableStateOf("") }
        if(name.isNotEmpty()){
            Text(
                text = "Hello , $name !",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h5
            )
        }

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text("Name") }
        )
    }
}


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
fun ConversationScreen(showBottomEvent:(Boolean)->Unit) {
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState() // State hoisted to the ConversationScreen

    MessagesList(SampleData.conversationSample, lazyListState,scope) // Reuse same state in MessageList

    UserInput(
        onMessageSent = { // Apply UI logic to lazyListState
            scope.launch {
                lazyListState.scrollToItem(0)
            }
        },
    )
    
    LaunchedEffect(""){
        showBottomEvent(false)
    }

    DisposableEffect(""){
        onDispose {
            showBottomEvent(true)
        }
    }
}

@Composable
private fun UserInput(onMessageSent:()->Unit){

}

@Composable
private fun MessagesList(
    messages: List<Message>,
    lazyListState: LazyListState = rememberLazyListState() ,// LazyListState has a default value
    scope: CoroutineScope
) {
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

