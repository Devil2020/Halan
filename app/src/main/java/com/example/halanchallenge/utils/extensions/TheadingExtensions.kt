package com.mohammedmorse.utils.extensions

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun Activity.navigateDelay(time: Long = 3000L, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        action.invoke()
    }, time)
}

inline fun <T> AppCompatActivity.collect(flow: Flow<T>, crossinline block: (T) -> Unit) =
    lifecycleScope.launch {
        flow
            .flowWithLifecycle(this@collect.lifecycle)
            .collect { action ->
                block.invoke(action)
            }
    }

inline fun <T> ViewModel.collect(flow: Flow<T>, crossinline block: (T) -> Unit) =
    viewModelScope.launch {
        flow
            .onEach { block.invoke(it) }
            .launchIn(viewModelScope)
    }
