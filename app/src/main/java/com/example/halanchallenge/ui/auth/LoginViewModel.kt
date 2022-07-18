package com.example.halanchallenge.ui.auth

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.usecase.IUserGateway
import com.example.halanchallenge.utils.validator.InputValidator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class LoginViewModel(private val gateway: IUserGateway) : ViewModel() {

    // For Ui Validation
    val userNameValidator = MutableLiveData<String>()
    val passwordValidator = MutableLiveData<String>()
    val enableLogInButton = MediatorLiveData<Boolean>().apply {
        var usernameFlag = true
        var passwordFlag = true
        value = false
        addSource(userNameValidator) { x ->
            x?.let {
                usernameFlag = !InputValidator.isUsernameValid(it)
                value = passwordFlag == false && usernameFlag == false
            }
        }
        addSource(passwordValidator) { x ->
            x?.let {
                passwordFlag = !InputValidator.isPasswordValid(it)
                value = passwordFlag == false && usernameFlag == false
            }
        }

    }

    // For Intentions and States
    private var intents = MutableSharedFlow<LoginIntents>()

    @FlowPreview
    private val response: Flow<LoginState> by lazy { handleIntentsAndProduceStates() }

    fun processIntents(listOfIntents: Flow<LoginIntents>) {
        listOfIntents
            .onEach {
                intents.emit(it)
            }
            .launchIn(viewModelScope)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @FlowPreview
    private fun handleIntentsAndProduceStates(): Flow<LoginState> {
        return intents
            .flatMapConcat { intent ->
                when (intent) {
                    is LoginIntents.Login -> return@flatMapConcat gateway.executeLoginUseCase(
                        LoginRequest(
                            username = userNameValidator.value ?: "",
                            password = passwordValidator.value ?: ""
                        )
                    )

                    is LoginIntents.SaveToken -> return@flatMapConcat flow {
                        gateway.executeSaveTokenUseCase(
                            intent.token
                        )

                    }

                    is LoginIntents.SaveProfile -> return@flatMapConcat flow {
                        gateway.executeSaveProfileUseCase(
                            intent.profile
                        )
                    }

                    is LoginIntents.MakeItLogggedIn -> return@flatMapConcat flow {
                        gateway.executeSetIsLoggedInUseCase(
                            intent.isLoggedIn
                        )
                    }

                    else -> {
                        return@flatMapConcat emptyFlow()
                    }
                }
            }
            .scan(InitialLoginState) { old: LoginState, new: LoginState ->
                if (new.isLoading == true) {
                    old.copy(true, null, null)
                } else if (new.error != null) {
                    old.copy(null, error = new.error, null)
                } else if (new.loginResponse != null) {
                    old.copy(null, null, new.loginResponse)
                } else {
                    new
                }
            }

    }

    @FlowPreview
    fun getStatus() = response
}