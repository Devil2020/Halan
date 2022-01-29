package com.example.halanchallenge.ui.splash

import androidx.lifecycle.ViewModel
import com.example.halanchallenge.domain.repository.IUserRepository

class SplashViewModel(private val repository: IUserRepository) : ViewModel() {

    fun isLoggedIn () = repository.isLoggedIn()

}