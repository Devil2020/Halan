package com.example.halanchallenge.ui.products.list

import com.example.halanchallenge.utils.base.Intent

sealed class ProductsIntents : Intent {
    object GetProducts : ProductsIntents()
    object Logout : ProductsIntents()
}
