package com.example.halanchallenge.utils.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.expertapps.base.dialog.Loader
import kotlinx.coroutines.flow.Flow


abstract class BaseActivity<B : ViewDataBinding, IntentType : Intent, StateType : State> :
    AppCompatActivity() {

    val loader: Loader by lazy {
        Loader()
    }

    var binding: B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindDataBinnding()
    }

    abstract fun bindDataBinnding(): B

    abstract fun render ( state : StateType )

    abstract fun collectOurIntents () : Flow<IntentType>

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}