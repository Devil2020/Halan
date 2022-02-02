package com.example.halanchallenge.domain.usecase

import com.example.halanchallenge.domain.entities.login.LoginRequest
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.entities.product.ProductRequest
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.ui.auth.LoginState
import com.example.halanchallenge.ui.products.list.ProductsState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart


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