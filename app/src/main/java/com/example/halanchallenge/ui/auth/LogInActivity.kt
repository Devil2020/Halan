package com.example.halanchallenge.ui.auth

import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.halanchallenge.BuildConfig
import com.example.halanchallenge.R
import com.example.halanchallenge.app.HalanCoordinator
import com.example.halanchallenge.app.HalanDirections
import com.example.halanchallenge.databinding.ActivityLogInBinding
import com.example.halanchallenge.ui.entities.Intent
import com.example.halanchallenge.ui.entities.State
import com.example.halanchallenge.utils.base.BaseActivity
import com.example.halanchallenge.utils.base.MviView
import com.expertapps.base.extensions.showSnackbar
import com.mohammedmorse.utils.extensions.collect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

class LogInActivity : BaseActivity<ActivityLogInBinding>(), MviView<Intent, State> {

    private val userIntentions = MutableSharedFlow<Intent>()
    private val vm: LoginViewModel by viewModel()

    override fun bindDataBinnding(): ActivityLogInBinding {
        return DataBindingUtil.setContentView<ActivityLogInBinding>(this, R.layout.activity_log_in)
            ?.apply {
                arabicName = BuildConfig.RIGHT
                englishName = BuildConfig.LEFT
                viewmodel = vm
                doOnLoginClick = {
                    userIntentions.tryEmit(Intent.Login)
                }
            }!!
    }

    override fun onStart() {
        super.onStart()
        vm.processIntents(collectOurIntents())
        collect(vm.getStatus(), ::render)
    }

    override fun render(state: State) {
        when (state) {
            is State.Loading -> {
                loader.show(WeakReference(this))
            }
            is State.Error -> {
                loader.hide()
                showSnackbar(
                    this,
                    "Sorry For This Error , but please check this out ${state.message} 🖐❌",
                    Toast.LENGTH_LONG,
                    "Try Again"
                ) {
                    userIntentions.tryEmit(Intent.Login)
                }
            }
            is State.Success<*> -> {
                loader.hide()
                HalanCoordinator.navigate(HalanDirections.ProductsList(this))
            }
            else -> {
                loader.hide()
                showSnackbar(
                    this,
                    "Some Thing Happened That I didn`t handle it 🤐🤷‍♂️",
                    Toast.LENGTH_LONG
                ) {}
            }
        }
    }

    override fun collectOurIntents(): Flow<Intent> {
        return userIntentions
    }

}