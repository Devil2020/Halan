package com.example.halanchallenge.ui.products.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.halanchallenge.databinding.ImageViewItemBinding
import com.example.halanchallenge.utils.extensions.renderImage

class ImagesAdapter (private val images: List<String>) : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagesAdapter.ImageViewHolder {
        return ImageViewHolder(
            ImageViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ImagesAdapter.ImageViewHolder, position: Int) {
        holder.renderImage(images[position])
    }

    override fun getItemCount(): Int {
        return images.count()
    }

    inner class ImageViewHolder(private val db: ImageViewItemBinding) :
        RecyclerView.ViewHolder(db.root) {

        fun renderImage(image: String) {
            db.renderImage(image)
        }

    }

}