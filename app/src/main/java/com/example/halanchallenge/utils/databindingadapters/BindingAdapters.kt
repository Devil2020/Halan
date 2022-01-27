package com.example.halanchallenge.utils

import android.text.TextUtils
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.example.halanchallenge.R
import com.example.halanchallenge.utils.validator.InputValidator
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorUserName")
fun TextInputLayout.errorUserName(dataLiveData: LiveData<String>) {

    findViewTreeLifecycleOwner()?.let {
        dataLiveData.observe(it) {
            if (TextUtils.isEmpty(it)) {
                error = null
            } else {
                if (it.length >= 4
                ) {
                    error = null
                } else {
                    error = resources.getString(R.string.wrong_username_label)
                }
            }
        }
    }
}


@BindingAdapter("errorPassword")
fun TextInputLayout.errorPassword(dataLiveData: LiveData<String>) {
    findViewTreeLifecycleOwner()?.let {
        dataLiveData.observe(it) {
            if (TextUtils.isEmpty(it)) {
                error = null
            } else {
                if (InputValidator.isPasswordValid(it)
                ) {
                    error = null
                } else {
                    error =
                        resources.getString(R.string.wrong_password_label)
                }
            }
        }
    }
}