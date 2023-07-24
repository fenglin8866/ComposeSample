package com.xxh.sample.state.basic

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.xxh.sample.common.data.Message

class Suggestion {

}


enum class DrawerContent {
    Empty
}

class MessagesRepository {
    fun getSuggestions(it: String): List<Suggestion> {
        TODO("Not yet implemented")
    }

    fun getLatestMessages(channelId: String): Flow<List<Message>> {
        TODO("Not yet implemented")
    }

}


/**
 * 状态提升ViewModel示例
 */
class ConversationViewModel(
    private val channelId: String,
    private val repository: MessagesRepository
) : ViewModel() {

    /*
     Hoisted state
     每当用户输入新输入时，应用都会调用业务逻辑以生成 suggestions。
     */
    var inputMessage by mutableStateOf("")
        private set

    val suggestions: StateFlow<List<Suggestion>> =
        snapshotFlow { inputMessage }
            .filter { hasSocialHandleHint(it) }
            .mapLatest { getHandle(it) }
            .mapLatest { repository.getSuggestions(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    private fun getHandle(it: String): String {
        TODO("Not yet implemented")
    }

    private fun hasSocialHandleHint(it: String): Boolean {
        TODO("Not yet implemented")
    }


    //获取屏幕状态
    val messages = repository
        .getLatestMessages(channelId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    // Business logic
    fun sendMessage(message: Message) {

    }

    fun updateInput(newInput: String) {
        inputMessage = newInput
    }


    val drawerState = DrawerState(initialValue = DrawerValue.Closed)

    private val _drawerContent = MutableStateFlow<DrawerContent>(DrawerContent.Empty)

    val drawerContent: StateFlow<DrawerContent> = _drawerContent.asStateFlow()

    //关闭Drawer，注意协程的上下文
    fun closeDrawer(uiScope: CoroutineScope) {
        viewModelScope.launch {
            withContext(uiScope.coroutineContext) {  // Use instead of the default context
                drawerState.close()
            }
            // Fetch drawer content and update state
            _drawerContent.update {
                return@update it
            }
        }
    }
}




