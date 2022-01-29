package com.example.halanchallenge.ui.products.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.domain.usecase.*
import com.example.halanchallenge.ui.entities.Intent
import com.example.halanchallenge.ui.entities.State
import com.example.halanchallenge.utils.base.MviViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class ProductListViewModel(
    private val userRepository: IUserRepository,
    private val productRepository: IProductRepository
) : ViewModel(), MviViewModel<Intent, State> {

    private var intents = MutableSharedFlow<Intent>()
    private val token: String by lazy {
        executeGetUserTokenUseCase(userRepository)
    }
    val profile: LoginResponse.Profile by lazy {
        executeGetProfileUseCase(userRepository)
    }
    @FlowPreview
    private val response: Flow<State> by lazy { handleIntentsAndProduceStates() }

    override fun processIntents(listOfIntents: Flow<Intent>) {
        listOfIntents
            .distinctUntilChanged()
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
                    is Intent.GetProducts -> return@flatMapConcat executeGetProductsUseCase(
                        productRepository,
                        token
                    )

                    is Intent.Logout -> return@flatMapConcat flow {
                        executeLogOutUseCase(
                            userRepository
                        )
                        emit(State.Nothing)
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