package com.xxh.sample.others.compose

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class UiState {
    object SignedOut : UiState()
    object InProgress : UiState()
    object Error : UiState()
    object SignIn : UiState()
}

class LoginViewModel : ViewModel() {

    private val _uiState = mutableStateOf<UiState>(UiState.SignedOut)
    val uiState: State<UiState>
        get() = _uiState


    private val _uiState2 = MutableLiveData<UiState>(UiState.SignedOut)
    val uiState2: LiveData<UiState>
        get() = _uiState2

}
