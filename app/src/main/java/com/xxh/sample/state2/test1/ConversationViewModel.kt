package com.xxh.sample.state2.test1

import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessagesRepository {

}

class ConversationViewModel(
    private val channelId: String,
    private val messagesRepository: MessagesRepository
) : ViewModel() {

  /*  val messages = messagesRepository
        .getLatestMessages(channelId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    // Hoisted state
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

    fun updateInput(newInput: String) {
        inputMessage = newInput
    }

    // Business logic
    fun sendMessage(message: Message) { *//* ... *//*
    }

    val drawerState = DrawerState(initialValue = DrawerValue.Closed)

    private val _drawerContent = MutableStateFlow<DrawerContent>(DrawerContent.Empty)
    val drawerContent: StateFlow<DrawerContent> = _drawerContent.asStateFlow()

    fun closeDrawer(uiScope: CoroutineScope) {
        viewModelScope.launch {
            withContext(uiScope.coroutineContext) {  // Use instead of the default context
                drawerState.close()
            }
            // Fetch drawer content and update state
            _drawerContent.update { ... }
        }
    }*/
}



