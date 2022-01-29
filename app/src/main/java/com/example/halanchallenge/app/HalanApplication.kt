package com.example.halanchallenge.app

import android.app.Application

class HalanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        HalanDependencyInjector.inject()
    }

}