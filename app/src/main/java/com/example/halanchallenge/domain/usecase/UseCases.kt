package com.example.halanchallenge.domain.usecase

import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.entities.product.ProductRequest
import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.ui.auth.LoginState
import com.example.halanchallenge.ui.products.list.ProductsState
import com.example.halanchallenge.utils.usecase.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

abstract class IUserGateway : UseCase() {
    /*We must to make the validation for the login here , if the username valid or not*/
    abstract fun executeLoginUseCase(request: LoginRequest): Flow<State<LoginResponse>>
    abstract fun executeSaveTokenUseCase(token: String)
    abstract fun executeLoadTokenUseCase(): String
    abstract fun executeLogoutUseCase()
    abstract fun executeSaveProfileUseCase(profile: LoginResponse.Profile)
    abstract fun executeLoadProfileUseCase(): LoginResponse.Profile
    abstract fun executeGetIsLoggedInUseCase(): Boolean
    abstract fun executeSetIsLoggedInUseCase(isLoggedIn: Boolean)
}

class UserGateway(private val repository: IUserRepository) : IUserGateway() {

    override fun executeLoginUseCase(request: LoginRequest): Flow<State<LoginResponse>> {
        return executeSuspendUseCase {
            repository.loginUser(request)
        }
    }

    override fun executeSetIsLoggedInUseCase(isLoggedIn: Boolean) {
        repository.changeLoggingStatus(isLoggedIn)
    }

    override fun executeGetIsLoggedInUseCase() = repository.isLoggedIn()

    override fun executeSaveTokenUseCase(token: String) {
        repository.saveToken("Bearer $token")
    }

    override fun executeLoadTokenUseCase() = repository.loadToken()

    override fun executeSaveProfileUseCase(profile: LoginResponse.Profile) {
        repository.saveProfile(profile)
    }

    override fun executeLoadProfileUseCase() = repository.getProfile()

    override fun executeLogoutUseCase() {
        repository.logOutUser()
    }
}

/*=====================================================================================================================================================================================
  =====================================================================================================================================================================================*/

abstract class IProductsGateway : UseCase() {
    abstract fun executeGetProductsUseCase(token: String): Flow<State<ProductResponse>>
}

class ProductsGateway(private val repository: IProductRepository) : IProductsGateway() {
    override fun executeGetProductsUseCase(token: String): Flow<State<ProductResponse>> {
        return executeSuspendUseCase {
            repository.loadProductsList(ProductRequest(token))
        }
    }
}