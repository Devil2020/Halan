package com.example.halanchallenge.utils

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
            error = if (InputValidator.isUsernameValid(it)) {
                null
            } else {
                resources.getString(R.string.wrong_username_label)
            }
        }
    }
}


@BindingAdapter("errorPassword")
fun TextInputLayout.errorPassword(dataLiveData: LiveData<String>) {
    findViewTreeLifecycleOwner()?.let {
        dataLiveData.observe(it) {
            error = if (InputValidator.isPasswordValid(it)) {
                null
            } else {
                resources.getString(R.string.wrong_password_label)
            }
        }
    }
}