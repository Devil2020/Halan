package com.example.halanchallenge.ui.splash

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
import androidx.databinding.DataBindingUtil
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.halanchallenge.BuildConfig
import com.example.halanchallenge.R
import com.example.halanchallenge.app.AuthanticationDirection
import com.example.halanchallenge.app.HalanCoordinator
import com.example.halanchallenge.app.ProductListDirection
import com.example.halanchallenge.databinding.ActivitySplashBinding
import com.example.halanchallenge.utils.extensions.animateView
import com.example.halanchallenge.utils.extensions.run
import com.example.halanchallenge.utils.extensions.setInterpolator
import com.example.halanchallenge.utils.extensions.setUpListener
import com.mohammedmorse.utils.extensions.navigateDelay
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private var binding: ActivitySplashBinding? = null
    private val vm: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        binding?.apply {
            arabicName = BuildConfig.RIGHT
            englishName = BuildConfig.LEFT
        }
        animateApplicationName()
    }

    private fun animateApplicationName() {
        animateArabicWord {
            animateScaleBigDot()
            animateScaleAndTranslateSmallDot {
                animateAttentionRuppered{
                    navigate()
                }
            }
        }
        animateEnglishWord()
    }

    private inline fun animateAttentionRuppered(crossinline doOnEnd: (Animator) -> Unit) {
        val animatorSet = AnimatorSet()
        val object1: ObjectAnimator =
            ObjectAnimator.ofFloat(
                binding?.rotateCirculeImageView,
                "scaleX",
                1f,
                1.25f,
                0.75f,
                1.15f,
                1f
            )
        val object2: ObjectAnimator =
            ObjectAnimator.ofFloat(
                binding?.rotateCirculeImageView,
                "scaleY",
                1f,
                0.75f,
                1.25f,
                0.85f,
                1f
            )
        animatorSet.playTogether(object1, object2)
        with(animatorSet) {
            duration = 1000
            interpolator = FastOutSlowInInterpolator()
            addListener(onEnd = doOnEnd)
            this.start()
        }
    }

    private inline fun animateArabicWord(crossinline doOnEnd: () -> Unit) {
        binding?.arabicApplicationName?.animateView(R.anim.enter_arabic_word)
            .setUpListener(object : Animation.AnimationListener {

                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationStart(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    doOnEnd.invoke()
                }
            })
            .run()
    }

    private fun animateEnglishWord() {
        binding?.englishApplicationName?.animateView(R.anim.enter_english_word)
            .run()
    }

    private fun animateScaleBigDot() {
        binding?.scaleCirculeImageView?.animateView(R.anim.bounce_scale)
            .setInterpolator(BounceInterpolator())
            .setUpListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                    binding?.scaleCirculeImageView?.alpha = 1f

                }

                override fun onAnimationEnd(p0: Animation?) {

                }

                override fun onAnimationRepeat(p0: Animation?) {

                }
            })
            .run()
    }

    private inline fun animateScaleAndTranslateSmallDot(crossinline doOnEnd: (Animator) -> Unit) {
        val bottom = 0F
        val right = 0F
        val animatorSet = AnimatorSet()
        val object1 = ObjectAnimator.ofFloat(binding?.rotateCirculeImageView, "alpha", 0f, 1f)
        val object2 = ObjectAnimator.ofFloat(
            binding?.rotateCirculeImageView,
            "translationY",
            bottom,
            0f,
            -310f
        )
        val object3 = ObjectAnimator.ofFloat(
            binding?.rotateCirculeImageView,
            "translationX",
            right,
            0f,
            -310f
        )
        animatorSet.playTogether(object1, object2, object3)

        with(animatorSet) {
            duration = 1000
            interpolator = FastOutSlowInInterpolator()
            addListener(onEnd = doOnEnd)
            this.start()
        }
    }

    private fun navigate() {
        navigateDelay {
            if (vm.isLoggedIn()) {
                HalanCoordinator.navigate(ProductListDirection(this))
            } else {
                HalanCoordinator.navigate(AuthanticationDirection(this))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}