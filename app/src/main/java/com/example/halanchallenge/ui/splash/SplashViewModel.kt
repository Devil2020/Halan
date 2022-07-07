package com.example.halanchallenge.ui.splash

import androidx.lifecycle.ViewModel
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.domain.usecase.IUserGateway

class SplashViewModel(private val gateway: IUserGateway) : ViewModel() {

    fun isLoggedIn() = gateway.executeGetIsLoggedInUseCase()

}