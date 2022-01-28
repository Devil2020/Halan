package com.example.halanchallenge.ui.entities

sealed class Intent {

    object Login : Intent()

    object GetProducts : Intent()
}
