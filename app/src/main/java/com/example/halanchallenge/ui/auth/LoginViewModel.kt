package com.example.halanchallenge.ui.auth

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel : ViewModel() {

    // Validation
    val usernameValue = MutableStateFlow("")
    val passwordValue = MutableStateFlow("")
    // Error
    val usernameError = MutableStateFlow(false)
    val passwordError = MutableStateFlow(false)
    val passwordVisibility = MutableStateFlow(true)

}