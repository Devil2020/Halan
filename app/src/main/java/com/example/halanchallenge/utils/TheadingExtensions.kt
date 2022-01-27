package com.mohammedmorse.utils.extensions

import android.app.Activity
import android.os.Handler
import android.os.Looper
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