package com.example.halanchallenge.ui.auth

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.domain.usecase.IUserGateway
import com.example.halanchallenge.domain.usecase.UseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.Response

class LoginViewModel(
    private val usecases: IUserGateway
) : ViewModel() {

    fun logIn(usernameValue: String, passwordValue: String) =
        usecases.executeLoginUseCase(usernameValue, passwordValue)
            .onEach {  }
            .launchIn(viewModelScope)

}