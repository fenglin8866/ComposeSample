/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.samples.jetsurvey.signinsignup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samples.jetsurvey.Screen
import com.samples.jetsurvey.Screen.SignIn
import com.samples.jetsurvey.Screen.SignUp
import com.samples.jetsurvey.Screen.Survey
import com.samples.jetsurvey.util.Event

class WelcomeViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _navigateTo = MutableLiveData<Event<Screen>>()
    val navigateTo: LiveData<Event<Screen>> = _navigateTo

    fun handleContinue(email: String) {
        if (userRepository.isKnownUserEmail(email)) {
            _navigateTo.value = Event(SignIn)
        } else {
            _navigateTo.value = Event(SignUp)
        }
    }

    fun signInAsGuest() {
        userRepository.signInAsGuest()
        _navigateTo.value = Event(Survey)
    }
}

class WelcomeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
