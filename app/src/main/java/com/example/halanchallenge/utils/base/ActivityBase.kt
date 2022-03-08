package com.example.halanchallenge.utils.base

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.expertapps.base.dialog.Loader


abstract class BaseActivity< B : ViewDataBinding > : AppCompatActivity() {

    companion object Toaster {

        fun showMessage ( context: Context , message : String) {
            Toast.makeText( context , "🎯 The Message is ${message} ." , Toast.LENGTH_SHORT).show()
            Log.i(BaseActivity::class.java.name , "🎯 The Message is ${message} .")
        }

    }

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