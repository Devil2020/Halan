package com.example.halanchallenge.ui.auth

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.halanchallenge.BuildConfig
import com.example.halanchallenge.R
import com.example.halanchallenge.app.HalanCoordinator
import com.example.halanchallenge.app.ProductListDirection
import com.example.halanchallenge.databinding.ActivityLogInBinding
import com.example.halanchallenge.utils.base.BaseActivity
import com.expertapps.base.extensions.showSnackbar
import com.mohammedmorse.utils.extensions.collect
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

class LogInActivity : BaseActivity<ActivityLogInBinding>() {

    private val userIntentions = MutableSharedFlow<LoginIntents>(
        replay = Int.MAX_VALUE,
        extraBufferCapacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val vm: LoginViewModel by viewModel()

    override fun bindDataBinnding(): ActivityLogInBinding {
        return DataBindingUtil.setContentView<ActivityLogInBinding>(this, R.layout.activity_log_in)
            ?.apply {
                arabicName = BuildConfig.RIGHT
                englishName = BuildConfig.LEFT
                viewmodel = vm
                doOnLoginClick = {
                    userIntentions.tryEmit(LoginIntents.Login)
                }
            }!!
    }

    @OptIn(FlowPreview::class)
    override fun onStart() {
        super.onStart()
        vm.processIntents(collectOurIntents())
        collect(vm.getStatus(), ::render)
    }

    private fun render(state: LoginState) {
        if (state.isLoading != null) {
            loader.show(WeakReference(this))
        } else if (state.error != null) {
            loader.hide()
            showSnackbar(
                this,
                "Sorry For This Error , but please check this out ${state.error.message} 🖐❌",
                Toast.LENGTH_LONG,
                "Try Again"
            ) {
                userIntentions.tryEmit(LoginIntents.Login)
            }
        } else if (state.loginResponse != null) {
            loader.hide()
            HalanCoordinator.navigate(ProductListDirection(this))
                .also {
                    userIntentions.tryEmit(LoginIntents.MakeItLogggedIn(true))
                    userIntentions.tryEmit(LoginIntents.SaveToken((state.loginResponse).token!!))
                    userIntentions.tryEmit(LoginIntents.SaveProfile((state.loginResponse).profile!!))
                }
        }

    }

    private fun collectOurIntents(): Flow<LoginIntents> {
        return userIntentions
    }

}