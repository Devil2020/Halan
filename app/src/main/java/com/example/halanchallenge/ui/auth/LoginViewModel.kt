package com.example.halanchallenge.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.domain.usecase.executeLoginUserUseCase
import com.example.halanchallenge.ui.entities.Intent
import com.example.halanchallenge.ui.entities.State
import com.example.halanchallenge.utils.base.MviViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/*intents
       ?.distinctUntilChanged()
       ?.map { convertIntentToActions(it) }
       ?.compose(homeAnnotateProcessor?.homeViewModelAnnotateProcessor)
       ?.scan(HomeState(), resultMaker)!!



}*/

class LoginViewModel(val repository: IUserRepository) : ViewModel(),
    MviViewModel<Intent, State> {

    // For Ui Validation
    val userNameValidator = MutableLiveData<String>()
    val passwordValidator = MutableLiveData<String>()

    // For Intentions and States
    private var intents = MutableSharedFlow<Intent>()
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
                    else -> {
                        return@flatMapConcat emptyFlow()
                    }
                }
            }
    }

    override fun getStatus() = response
}