package com.example.halanchallenge.app

import android.app.Application
import android.util.Log
import com.example.halanchallenge.app.di.HalanDependencyInjector

class HalanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        handleException()
    }

    private fun initKoin() {
        HalanDependencyInjector.inject(applicationContext)
    }

    private fun handleException() {
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            Log.e(HalanApplication::class.simpleName, "The Exception Happend Because ${e.message}")
        }
    }

}