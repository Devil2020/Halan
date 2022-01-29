package com.example.halanchallenge.ui.splash

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.halanchallenge.BuildConfig
import com.example.halanchallenge.R
import com.example.halanchallenge.app.HalanCoordinator
import com.example.halanchallenge.app.HalanDirections
import com.example.halanchallenge.databinding.ActivitySplashBinding
import com.example.halanchallenge.utils.extensions.animateView
import com.example.halanchallenge.utils.extensions.run
import com.example.halanchallenge.utils.extensions.setInterpolator
import com.example.halanchallenge.utils.extensions.setUpListener
import com.mohammedmorse.utils.extensions.navigateDelay
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    var binding: ActivitySplashBinding? = null
    val vm: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        binding?.apply {
            arabicName = BuildConfig.RIGHT
            englishName = BuildConfig.LEFT
        }
        animateApplicationName()
    }

    fun animateApplicationName() {
        binding?.arabicApplicationName?.animateView(R.anim.enter_arabic_word)
            .setUpListener(object : Animation.AnimationListener {

                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationStart(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    binding?.scaleCirculeImageView?.animateView(R.anim.bounce_scale)
                        .setInterpolator(BounceInterpolator())
                        .setUpListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(p0: Animation?) {
                                binding?.scaleCirculeImageView?.alpha = 1f
                            }

                            override fun onAnimationEnd(p0: Animation?) {
                                navigate()
                            }

                            override fun onAnimationRepeat(p0: Animation?) {

                            }
                        })
                        .run()
                }
            })
            .run()
        binding?.englishApplicationName?.animateView(R.anim.enter_english_word)
            .run()
    }

    fun navigate() {
        navigateDelay {
            if (vm.isLoggedIn()) {
                HalanCoordinator.navigate(HalanDirections.ProductsList(this))
            } else {
                HalanCoordinator.navigate(HalanDirections.Auth(this))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}