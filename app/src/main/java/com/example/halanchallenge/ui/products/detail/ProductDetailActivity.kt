package com.example.halanchallenge.ui.products.detail

import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.halanchallenge.R
import com.example.halanchallenge.databinding.ActivityProductDetailBinding
import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.utils.base.BaseActivity
import com.example.halanchallenge.utils.base.Constants
import com.example.halanchallenge.utils.extensions.bindProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding, Nothing, Nothing>() {

    val product: ProductResponse.Product by lazy {
        intent.getParcelableExtra(Constants.PRODUCT_DETAIL)!!
    }

    override fun bindDataBinnding(): ActivityProductDetailBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_product_detail)
    }

    override fun render(state: Nothing) {

    }

    override fun collectOurIntents(): Flow<Nothing> {
        return flowOf()
    }

    override fun onStart() {
        super.onStart()
        binding?.bindProduct(product)
    }

    fun back(view: View) {
        this.finish()
    }


}