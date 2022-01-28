package com.example.halanchallenge.utils.base

import android.content.ClipData
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.expertapps.base.dialog.Loader


abstract class BaseActivity< B : ViewDataBinding > : AppCompatActivity() {

    val loader: Loader by lazy {
        Loader()
    }

    var binding: B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindDataBinnding()
    }

    abstract fun bindDataBinnding(): B

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}