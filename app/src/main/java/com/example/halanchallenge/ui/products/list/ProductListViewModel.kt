package com.example.halanchallenge.ui.products.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.domain.usecase.*
import com.example.halanchallenge.ui.auth.InitialLoginState
import com.example.halanchallenge.ui.auth.LoginState
import com.example.halanchallenge.utils.base.MviViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

class ProductListViewModel(
    private val userGateway: IUserGateway,
    private val productGateway: IProductsGateway
) : ViewModel(), MviViewModel<ProductsIntents, ProductsState> {

    private var intents = MutableSharedFlow<ProductsIntents>()
    private val token: String by lazy {
        userGateway.executeLoadTokenUseCase()
    }
    val profile: LoginResponse.Profile by lazy {
        userGateway.executeLoadProfileUseCase()
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
            .flatMapConcat { intent ->
                when (intent) {
                    is ProductsIntents.GetProducts -> return@flatMapConcat productGateway.executeGetProductsUseCase(
                        token
                    )

                    is ProductsIntents.Logout -> return@flatMapConcat flow {
                        userGateway.executeLogoutUseCase()
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
                        return@flatMapConcat emptyFlow()
                    }
                }
            }
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

    @FlowPreview
    override fun getStatus() = response
}