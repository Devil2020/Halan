package com.example.halanchallenge.ui.products.list

import androidx.lifecycle.ViewModel
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository

class ProductListViewModel(
    private val userRepository: IUserRepository,
    private val productRepository: IProductRepository
) : ViewModel() {



}