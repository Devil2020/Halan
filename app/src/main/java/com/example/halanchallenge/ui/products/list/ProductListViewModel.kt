package com.example.halanchallenge.ui.products.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.domain.usecase.executeGetProductsUseCase
import com.example.halanchallenge.domain.usecase.executeGetProfileUseCase
import com.example.halanchallenge.domain.usecase.executeGetUserTokenUseCase
import com.example.halanchallenge.domain.usecase.executeLogOutUseCase
import com.example.halanchallenge.utils.base.Intent
import com.example.halanchallenge.utils.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class ProductListViewModel(
    private val userRepository: IUserRepository,
    private val productRepository: IProductRepository
) : BaseViewModel<ProductsIntents, ProductsState> (){

    private val intents = MutableSharedFlow<ProductsIntents>()
    private val token: String by lazy {
        executeGetUserTokenUseCase(userRepository)
    }
    val profile: LoginResponse.Profile by lazy {
        executeGetProfileUseCase(userRepository)
    }

    @FlowPreview
    private val response: Flow<ProductsState> by lazy { handleIntentsAndProduceStates() }

    override fun processIntents(listOfIntents: Flow<ProductsIntents>) {
        listOfIntents
            .distinctUntilChanged()
            .onEach {
                intents.emit(it)
            }
            .launchIn(viewModelScope)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @FlowPreview
    private fun handleIntentsAndProduceStates(): Flow<ProductsState> {
        return intents
            .flatMapMerge(concurrency = DEFAULT_CONCURRENCY, transform = ::toState)
            .scan(InitialProductsState) { old: ProductsState, new: ProductsState ->
                if (new.isLoading == true) {
                    old.copy(true, null, null, null)
                } else if (new.error != null) {
                    old.copy(null, error = new.error, null, null)
                } else if (new.productsResponse != null) {
                    old.copy(null, null, new.productsResponse, null)
                } else if (new.isLogOut != null) {
                    old.copy(null, null, null, true)
                } else {
                    new
                }

            }
    }

    override fun toState(intent: Intent): Flow<ProductsState> {
        return when (intent) {
            is ProductsIntents.GetProducts -> return executeGetProductsUseCase(
                productRepository,
                token
            )

            is ProductsIntents.Logout -> return flow {
                executeLogOutUseCase(
                    userRepository
                )
                emit(
                    ProductsState(
                        isLoading = false,
                        error = null,
                        productsResponse = null,
                        isLogOut = true
                    )
                )
            }

            else -> {
                return emptyFlow()
            }
        }
    }

    @FlowPreview
    override fun getStatus() = response
}