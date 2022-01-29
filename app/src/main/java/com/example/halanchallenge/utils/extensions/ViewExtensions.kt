package com.example.halanchallenge.utils.extensions

import android.content.Context
import android.os.Build
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.halanchallenge.R
import com.example.halanchallenge.databinding.ActivityProductDetailBinding
import com.example.halanchallenge.databinding.ActivityProductsBinding
import com.example.halanchallenge.databinding.ImageViewItemBinding
import com.example.halanchallenge.databinding.ProductItemBinding
import com.example.halanchallenge.domain.entities.login.LoginResponse
import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.ui.products.detail.ImagesAdapter
import com.example.halanchallenge.utils.base.Constants
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView

fun Context.isArabic() = getResources().getConfiguration().locale.language.lowercase().equals("ar")

fun AppCompatImageView.loadImage(url: String, cornerRadius: Int) {
    Glide.with(context).load(url).transform(RoundedCorners(cornerRadius)).into(this)
}

fun CircleImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ActivityProductsBinding.bindProfile(profile: LoginResponse.Profile) {
    val context = root.context
    WelcomeMessageTextView.text = context.getString(
        R.string.helloMessage,
        if (context.isArabic()) profile.name else profile.username
    )
    userName.text = profile.username
    userEmail.text = profile.email
    userPhone.text = profile.phone
    userIv.loadImage(profile.image ?: Constants.REPLACMENT_IMAGE_URL)
}

fun ProductItemBinding.bind(
    product: ProductResponse.Product,
    listener: (ProductResponse.Product) -> Unit
) {
    productItemTitleTv.text = if (root.context.isArabic()) product.nameAr else product.nameEn
    productIv.loadImage(
        url = product.image ?: Constants.REPLACMENT_PRODUCT_IMAGE_URL,
        cornerRadius = 15
    )
    moreBtn.setOnClickListener {
        listener.invoke(product)
    }
}

fun ImageViewItemBinding.renderImage(image: String) {
    productImageIV.loadImage(image, 10)
}

fun ActivityProductDetailBinding.bindProduct(product: ProductResponse.Product) {
    productTitleTv.text = if (root.context.isArabic()) product.nameAr else product.nameEn
    productDescriptionTv.text = product.dealDescription
    productPriceTv.text = root.context.getString(R.string.cache_price, product.price.toString())
    productImagesBanner.adapter = ImagesAdapter(product.images)
    arIndicator.attachTo(productImagesBanner, true)
}

fun RecyclerView.animateExtendedFab(extendedFab: ExtendedFloatingActionButton?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
                extendedFab?.shrink()
            } else {
                extendedFab?.extend()
            }
        }
    }
}

fun View?.animateView(animationRes: Int): View? {
    this?.animation = AnimationUtils.loadAnimation(this?.context, animationRes)
    return this
}

fun View?.setUpListener(listerner: Animation.AnimationListener? = null): View? {
    this?.animation?.setAnimationListener(listerner)
    return this
}

fun View?.setInterpolator(inter: Interpolator? = null): View? {
    this?.animation?.interpolator = inter
    return this
}

fun View?.run() = this?.animation?.startNow()

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.isGone() = this.visibility == View.GONE

fun View.visible() {
    if (isGone()) {
        this.visibility = View.VISIBLE
    }
}

fun View.gone() {
    if (isVisible()) {
        this.visibility = View.GONE
    }
}
