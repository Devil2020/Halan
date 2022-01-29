package com.example.halanchallenge.ui.auth

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.domain.usecase.executeLoginUserUseCase
import com.example.halanchallenge.domain.usecase.executeSaveProfileUseCase
import com.example.halanchallenge.domain.usecase.executeSaveUserTokenUseCase
import com.example.halanchallenge.domain.usecase.executeSetIsLoggedInUseCase
import com.example.halanchallenge.ui.entities.Intent
import com.example.halanchallenge.ui.entities.State
import com.example.halanchallenge.utils.base.MviViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class LoginViewModel(private val repository: IUserRepository) : ViewModel(),
    MviViewModel<Intent, State> {

    // For Ui Validation
    val userNameValidator = MutableLiveData<String>()
    val passwordValidator = MutableLiveData<String>()
    val enableLogInButton = MediatorLiveData<Boolean>().apply {
        value = true
    }

    // For Intentions and States
    private var intents = MutableSharedFlow<Intent>()

    @FlowPreview
    private val response: Flow<State> by lazy { handleIntentsAndProduceStates() }

    override fun processIntents(listOfIntents: Flow<Intent>) {
        listOfIntents
            .onEach {
                intents.emit(it)
            }
            .launchIn(viewModelScope)
    }

    @FlowPreview
    private fun handleIntentsAndProduceStates(): Flow<State> {
        return intents
            .flatMapConcat { intent ->
                when (intent) {
                    is Intent.Login -> return@flatMapConcat executeLoginUserUseCase(
                        repository,
                        LoginRequest(
                            username = userNameValidator.value ?: "",
                            password = passwordValidator.value ?: ""
                        )
                    )

                    is Intent.SaveToken -> return@flatMapConcat flow {
                        executeSaveUserTokenUseCase(
                            repository,
                            intent.token
                        )

                    }

                    is Intent.SaveProfile -> return@flatMapConcat flow {
                        executeSaveProfileUseCase(
                            repository,
                            intent.profile
                        )
                    }

                    is Intent.MakeItLogggedIn -> return@flatMapConcat flow {
                        executeSetIsLoggedInUseCase(
                            repository,
                            intent.isLoggedIn
                        )
                    }

                    else -> {
                        return@flatMapConcat emptyFlow()
                    }
                }
            }
    }

    @FlowPreview
    override fun getStatus() = response
}