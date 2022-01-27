package com.example.halanchallenge.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.halanchallenge.BuildConfig
import com.example.halanchallenge.R
import com.example.halanchallenge.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {

    var binding: ActivityLogInBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView<ActivityLogInBinding>(this, R.layout.activity_log_in)
                ?.apply {
                    arabicName = BuildConfig.RIGHT
                    englishName = BuildConfig.LEFT
                    doOnLoginClick = {
                        Toast.makeText(
                            this@LogInActivity,
                            "🚀🚀 You Clicked on Login .",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}