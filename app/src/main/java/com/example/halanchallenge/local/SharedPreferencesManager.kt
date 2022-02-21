package com.example.halanchallenge.local

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferencesManager constructor(private val sharedPrefsInstance: SharedPreferences) {

    fun putBoolean(tag: String, bool: Boolean) {
        sharedPrefsInstance.edit(true) { putBoolean(tag, bool) }
    }

    fun getBoolean(tag: String, bool: Boolean) : Boolean {
       return sharedPrefsInstance.getBoolean(tag, bool)
    }

    fun putString(tag: String, str: String?) {
        sharedPrefsInstance.edit(true) { putString(tag, str)  }
    }

    fun getString(tag: String, defStr: String? = null): String? {
        return sharedPrefsInstance.getString(tag, defStr)
    }

    fun clearPreferences(commit: Boolean = false) {
        sharedPrefsInstance.edit(commit) { clear() }
    }

}