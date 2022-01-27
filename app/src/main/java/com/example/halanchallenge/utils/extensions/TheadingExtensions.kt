package com.mohammedmorse.utils.extensions

import android.app.Activity
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

fun Activity.navigateDelay(time: Long = 3000L, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        action.invoke()
    }, time)
}

/*
inline fun <T> Activity.collect(flow: Flow<T>, crossinline block: (T) -> Unit) =
    lifecycleScope.launch {
        flow
            .flowWithLifecycle(this@collect.lifecycle)
            .collect { action ->
                block.invoke(action)
            }
    }
*/
