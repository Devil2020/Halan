package com.example.halanchallenge.ui.products.list

import android.content.Context
import android.telephony.TelephonyManager
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import com.example.halanchallenge.R
import com.example.halanchallenge.app.HalanCoordinator
import com.example.halanchallenge.app.HalanDirections
import com.example.halanchallenge.databinding.ActivityProductsBinding
import com.example.halanchallenge.utils.base.BaseActivity
import com.example.halanchallenge.utils.base.MviView
import com.example.halanchallenge.utils.extensions.animateExtendedFab
import com.example.halanchallenge.utils.extensions.bindProfile
import com.mohammedmorse.utils.extensions.collect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference
import java.util.*

class ProductsActivity : BaseActivity<ActivityProductsBinding>(),
    MviView<ProductsIntents, ProductsState> {

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

    private fun isTableTopMode(foldFeature: FoldingFeature?) =
        foldFeature?.state == FoldingFeature.State.HALF_OPENED &&
                foldFeature.orientation == FoldingFeature.Orientation.HORIZONTAL

    private fun isBookMode(foldFeature: FoldingFeature?) =
        foldFeature?.state == FoldingFeature.State.HALF_OPENED &&
                foldFeature.orientation == FoldingFeature.Orientation.VERTICAL

    private fun isFolded(foldFeature: FoldingFeature?) =
        foldFeature?.state == FoldingFeature.State.FLAT

    fun handleFoldableDevices() {
        lifecycleScope.launch(Dispatchers.Main) {
            WindowInfoTracker.getOrCreate(this@ProductsActivity)
                .windowLayoutInfo(this@ProductsActivity)
                .collect { newLayoutInfo ->
                    val foldingFeature: FoldingFeature? =
                        newLayoutInfo.displayFeatures.find { it is FoldingFeature } as? FoldingFeature
                    if (isTableTopMode(foldingFeature)) {
                        Toaster.showMessage(this@ProductsActivity , "Okay We Trigger is Half Opened with Orientation Horizontal ")
                    }
                    else if (isBookMode(foldingFeature)) {
                        Toaster.showMessage(this@ProductsActivity , "Okay We Trigger is Half Opened with Orientation Vertical ")
                    }
                    else if (isFolded(foldingFeature)){
                        Toaster.showMessage(this@ProductsActivity , "Okay We Fold the Device Right Now . ")
                        binding?.ProductsRecyclerView?.apply {
                            layoutManager = GridLayoutManager(this@ProductsActivity , 2)
                        }
                    }
                }
        }
    }

    fun isTablet () : Boolean {
        val manager = getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return manager.phoneType == TelephonyManager.PHONE_TYPE_NONE
    }

    override fun onStart() {
        super.onStart()
        handleFoldableDevices()
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
        userIntentions.tryEmit(ProductsIntents.GetProducts)
    }

    fun logOut(view: View) {
        userIntentions.tryEmit(ProductsIntents.Logout)
    }

    override fun render(state: ProductsState) {
        if (state.isLoading == true) {
            loader.show(WeakReference(this))
        }
        else if (state.error != null) {
            loader.hide()
            userIntentions.tryEmit(ProductsIntents.Logout)
            HalanCoordinator.navigate(HalanDirections.Auth(this))
        }
        else if (state.isLogOut == true) {
            HalanCoordinator.navigate(direction = HalanDirections.Auth(this))
        }
        else if (state.productsResponse != null) {
            loader.hide()
            adapter.submit(state.productsResponse.products!!)
        }
    }

    override fun collectOurIntents(): Flow<ProductsIntents> {
        return userIntentions
    }

}