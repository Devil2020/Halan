package com.example.halanchallenge.ui.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.arch.core.util.Function
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.domain.usecase.executeLoginUserUseCase
import com.example.halanchallenge.domain.usecase.executeSaveProfileUseCase
import com.example.halanchallenge.domain.usecase.executeSaveUserTokenUseCase
import com.example.halanchallenge.domain.usecase.executeSetIsLoggedInUseCase
import com.example.halanchallenge.utils.base.Intent
import com.example.halanchallenge.utils.base.BaseViewModel
import com.example.halanchallenge.utils.base.State
import com.example.halanchallenge.utils.validator.InputValidator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import java.util.function.BiFunction

class LoginViewModel(private val repository: IUserRepository) :
    BaseViewModel<LoginIntents, LoginState>() {

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
    private val intents = MutableSharedFlow<LoginIntents>()

    @FlowPreview
    private val response: Flow<LoginState> by lazy { handleIntentsAndProduceStates() }

    override fun processIntents(listOfIntents: Flow<LoginIntents>) {
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
            .flatMapMerge(concurrency = DEFAULT_CONCURRENCY, transform = ::toState)
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

    override fun toState(intent: Intent) = when (intent) {

        is LoginIntents.Login -> executeLoginUserUseCase(
            repository,
            LoginRequest(
                username = userNameValidator.value ?: "",
                password = passwordValidator.value ?: ""
            )
        )

        is LoginIntents.SaveToken -> flow {
            executeSaveUserTokenUseCase(
                repository,
                intent.token
            )

        }

        is LoginIntents.SaveProfile -> flow {
            executeSaveProfileUseCase(
                repository,
                intent.profile
            )
        }

        is LoginIntents.MakeItLogggedIn -> flow {
            executeSetIsLoggedInUseCase(
                repository,
                intent.isLoggedIn
            )
        }

        else -> {
            emptyFlow()
        }

    }

    @FlowPreview
    override fun getStatus() = response
}