package com.example.halanchallenge.ui.products.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.halanchallenge.databinding.ProductItemBinding
import com.example.halanchallenge.domain.entities.product.ProductResponse
import com.example.halanchallenge.utils.extensions.bind
import kotlinx.coroutines.delay

inline fun ProductsAdapter.withAction(noinline action: (ProductResponse.Product) -> Unit): ProductsAdapter {
    this.listener = action
    return this
}

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    @PublishedApi
    internal val data: ArrayList<ProductResponse.Product> = ArrayList()

    lateinit var listener: (ProductResponse.Product) -> Unit

    fun submit(newData: List<ProductResponse.Product>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.renderProduct(data[position])
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    inner class ProductViewHolder(private val db: ProductItemBinding) :
        RecyclerView.ViewHolder(db.root) {

        fun renderProduct(product: ProductResponse.Product) {
            db.bind(product, listener)
        }

    }

}