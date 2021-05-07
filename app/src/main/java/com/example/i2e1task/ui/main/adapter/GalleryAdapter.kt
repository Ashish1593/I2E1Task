package com.example.i2e1task.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.i2e1task.databinding.ImageItemLayoutFileBinding

class GalleryAdapter(
    private val users: List<String>
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    class GalleryViewHolder(
        private val imageItem: ImageItemLayoutFileBinding
    ) : RecyclerView.ViewHolder(imageItem.root) {
        fun bind(image: String) {
            Glide.with(itemView.context)
                .load(image)
                .transform(CenterCrop(), RoundedCorners(20))
                .into(imageItem.galleryImage)
        }

        fun clear() {
            Glide.with(itemView.context).clear(imageItem.galleryImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GalleryViewHolder(
            ImageItemLayoutFileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) =
        holder.bind(users[position])

    override fun onViewRecycled(holder: GalleryViewHolder) {
        super.onViewRecycled(holder)
        holder.clear()
    }
}