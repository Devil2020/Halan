package com.example.halanchallenge.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.domain.usecase.executeLoginUserUseCase
import com.example.halanchallenge.ui.entities.Intent
import com.example.halanchallenge.ui.entities.State
import com.example.halanchallenge.utils.base.MviViewModel
import kotlinx.coroutines.flow.*

/*intents
       ?.distinctUntilChanged()
       ?.map { convertIntentToActions(it) }
       ?.compose(homeAnnotateProcessor?.homeViewModelAnnotateProcessor)
       ?.scan(HomeState(), resultMaker)!!


   listOfIntents
       .distinctUntilChanged()
       .onEach {
           when (it) {
               is Intent.Login -> {
                   executeLogin()
               }
               else -> {}
           }
       }
       .launchIn(viewModelScope)
}*/

class LoginViewModel(private val repository: IUserRepository) : ViewModel(),
    MviViewModel<Intent, State> {

    // For Ui Validation
    val userNameValidator = MutableLiveData<String>()
    val passwordValidator = MutableLiveData<String>()

    // For Intentions and States
    private var intents: Flow<Intent> = MutableSharedFlow()
    private var response: Flow<State> = handleIntentsAndProduceStates()

    override fun processIntents(listOfIntents: Flow<Intent>) {
        intents.flatMapMerge {
            listOfIntents
        }
    }

    private fun handleIntentsAndProduceStates(): Flow<State> {
        return intents
            .distinctUntilChanged()
            .transform { intent ->
                when (intent) {
                    is Intent.Login -> executeLoginUserUseCase(
                        repository,
                        LoginRequest(
                            username = userNameValidator.value ?: "",
                            password = passwordValidator.value ?: ""
                        )
                    )
                    else -> {}
                }
            }
    }

    override fun getStatus() = response
}