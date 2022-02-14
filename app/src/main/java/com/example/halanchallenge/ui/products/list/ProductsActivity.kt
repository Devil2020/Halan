package com.example.halanchallenge.ui.products.list

import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.halanchallenge.R
import com.example.halanchallenge.app.AuthanticationDirection
import com.example.halanchallenge.app.HalanCoordinator
import com.example.halanchallenge.app.ProductDetailDirection
import com.example.halanchallenge.databinding.ActivityProductsBinding
import com.example.halanchallenge.utils.base.BaseActivity
import com.example.halanchallenge.utils.extensions.animateExtendedFab
import com.example.halanchallenge.utils.extensions.bindProfile
import com.mohammedmorse.utils.extensions.collect
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

class ProductsActivity : BaseActivity<ActivityProductsBinding,ProductsIntents, ProductsState>(){

    private val userIntentions = MutableSharedFlow<ProductsIntents>(
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
                        ProductDetailDirection(
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
        userIntentions.tryEmit(ProductsIntents.GetProducts)
    }

    fun logOut(view: View) {
        userIntentions.tryEmit(ProductsIntents.Logout)
    }

    override fun render(state: ProductsState) {

        if (state.isLoading == true) {
            loader.show(WeakReference(this))
        } else if (state.error != null) {
            loader.hide()
            userIntentions.tryEmit(ProductsIntents.Logout)
            HalanCoordinator.navigate(AuthanticationDirection(this))
        } else if (state.isLogOut == true) {
            HalanCoordinator.navigate(direction = AuthanticationDirection(this))
        } else if (state.productsResponse != null) {
            loader.hide()
            adapter.submit(state.productsResponse.products!!)
        }

    }

    override fun collectOurIntents(): Flow<ProductsIntents> {
        return userIntentions
    }

}