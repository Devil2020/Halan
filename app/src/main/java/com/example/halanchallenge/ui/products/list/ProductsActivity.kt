package com.example.halanchallenge.ui.products.list

import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.halanchallenge.R
import com.example.halanchallenge.app.HalanCoordinator
import com.example.halanchallenge.app.HalanDirections
import com.example.halanchallenge.databinding.ActivityProductsBinding
import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.ui.entities.Intent
import com.example.halanchallenge.ui.entities.State
import com.example.halanchallenge.utils.base.BaseActivity
import com.example.halanchallenge.utils.base.MviView
import com.example.halanchallenge.utils.extensions.animateExtendedFab
import com.example.halanchallenge.utils.extensions.bindProfile
import com.mohammedmorse.utils.extensions.collect
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

class ProductsActivity : BaseActivity<ActivityProductsBinding>(), MviView<Intent, State> {

    private val userIntentions = MutableSharedFlow<Intent>(
        replay = Int.MAX_VALUE,
        extraBufferCapacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    private val vm: ProductListViewModel by viewModel()
    private val adapter = ProductsAdapter()

    override fun bindDataBinnding(): ActivityProductsBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_products)
    }

    override fun onStart() {
        super.onStart()
        binding?.apply {
            ProductsRecyclerView.animateExtendedFab(LogOutFab)
            bindProfile(vm.profile)
            ProductsRecyclerView.adapter = adapter
                .withAction {
                    HalanCoordinator.navigate(
                        HalanDirections.ProductDetail(
                            this@ProductsActivity,
                            it
                        )
                    )
                }
        }
        vm.processIntents(collectOurIntents())
        collect(vm.getStatus(), ::render)
    }

    override fun onResume() {
        super.onResume()
        userIntentions.tryEmit(Intent.GetProducts)
    }

    fun logOut(view: View) {
        userIntentions.tryEmit(Intent.Logout)
    }

    override fun render(state: State) {
        when (state) {
            is State.Nothing -> {
                HalanCoordinator.navigate(direction = HalanDirections.Auth(this))
            }
            is State.Loading -> {
                loader.show(WeakReference(this))
            }
            is State.Error -> {
                loader.hide()
                userIntentions.tryEmit(Intent.Logout)
                HalanCoordinator.navigate(HalanDirections.Auth(this))
            }
            is State.Success<*> -> {
                loader.hide()
                adapter.submit((state.data as ProductResponse).products!!)
            }
        }
    }

    override fun collectOurIntents(): Flow<Intent> {
        return userIntentions
    }

}