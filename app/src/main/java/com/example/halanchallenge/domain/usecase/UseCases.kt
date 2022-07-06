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
    abstract fun executeLoadTokenUseCase(token: String)
    abstract fun executeLogoutUseCase(): Flow<State<Boolean>>
    abstract fun executeSaveProfileUseCase()
    abstract fun executeLoadProfileUseCase()
}

class UserGateway(private val repository: IUserRepository) : IUserGateway() {

    override fun executeLoginUseCase(request: LoginRequest): Flow<State<LoginResponse>> {
        return executeSuspendUseCase {
            repository.loginUser(request)
        }
    }

    override fun executeSaveTokenUseCase(token: String) {
        TODO("Not yet implemented")
    }

    override fun executeLoadTokenUseCase(token: String) {
        TODO("Not yet implemented")
    }

    override fun executeSaveProfileUseCase() {
        TODO("Not yet implemented")
    }

    override fun executeLoadProfileUseCase() {
        TODO("Not yet implemented")
    }

    override fun executeLogoutUseCase(): Flow<State<Boolean>> {
        TODO("Not yet implemented")
    }
}

/*=====================================================================================================================================================================================
  =====================================================================================================================================================================================*/

abstract class IProductsGateway : UseCase() {
    abstract fun executeGetProductsUseCase(token: String): Flow<State<ProductResponse>>
}

class ProductsGateway : IProductsGateway() {
    override fun executeGetProductsUseCase(token: String): Flow<State<ProductResponse>> {
        TODO("Not yet implemented")
    }
}

fun executeLoginUserUseCase(repository: IUserRepository, request: LoginRequest) =
    flow {
        emit(
            LoginState(
                isLoading = false,
                error = null,
                loginResponse = repository.loginUser(request)
            )
        )
    }
        .onStart {
            emit(LoginState(isLoading = true, error = null, loginResponse = null))
        }
        .catch {
            val exception = it
            emit(LoginState(isLoading = false, error = exception, loginResponse = null))
        }

fun executeGetProductsUseCase(repository: IProductRepository, token: String) =
    flow {
        emit(
            ProductsState(
                isLoading = false,
                error = null,
                productsResponse = repository.loadProductsList(ProductRequest(token)),
                isLogOut = null
            )
        )
    }
        .onStart {
            emit(
                ProductsState(
                    isLoading = true,
                    error = null,
                    productsResponse = null,
                    isLogOut = null
                )
            )
        }
        .catch {
            val exception = it
            emit(
                ProductsState(
                    isLoading = null,
                    error = exception,
                    productsResponse = null,
                    isLogOut = null
                )
            )
        }

fun executeSetIsLoggedInUseCase(repository: IUserRepository, isLoggedIn: Boolean) =
    repository.changeLoggingStatus(isLoggedIn)

fun executeSaveProfileUseCase(repository: IUserRepository, profile: LoginResponse.Profile) =
    repository.saveProfile(profile)

fun executeGetProfileUseCase(repository: IUserRepository) = repository.getProfile()

fun executeLogOutUseCase(repository: IUserRepository) = repository.logOutUser()

fun executeSaveUserTokenUseCase(repository: IUserRepository, token: String) =
    repository.saveToken("Bearer $token")

fun executeGetUserTokenUseCase(repository: IUserRepository) = repository.loadToken()