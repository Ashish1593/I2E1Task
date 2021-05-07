package com.example.i2e1task.ui.main.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.i2e1task.data.model.User
import kotlin.math.ceil
import com.example.i2e1task.databinding.ItemLayoutBinding
import com.example.i2e1task.utils.HEIGHT_CELL
import com.example.i2e1task.utils.LARGE_SPAN_SIZE
import com.example.i2e1task.utils.SMALL_SPAN_SIZE


class MainAdapter :
    PagingDataAdapter<User, MainAdapter.DataViewHolder>(UserComparator()) {

    class DataViewHolder(
        private val itemLayout: ItemLayoutBinding
    ) : RecyclerView.ViewHolder(itemLayout.root) {

        private lateinit var galleryAdapter: GalleryAdapter

        fun bind(user: User) {
            itemLayout.textViewUserName.text = user.name
            Glide.with(itemView.context)
                .load(user.userImage)
                .circleCrop()
                .into(itemLayout.imageViewAvatar)
            setupGalleryAdapter(user)
        }

        private fun setupGalleryAdapter(user: User) {
            val isEven = user.galleryImages.size % 2 == 0

            val gridLayoutManager =
                GridLayoutManager(itemView.context, 2, RecyclerView.VERTICAL, false)
            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (!isEven && position == 0) {
                        return LARGE_SPAN_SIZE
                    } else {
                        return SMALL_SPAN_SIZE
                    }
                }
            }

            val numberOfLines = ceil(user.galleryImages.size / 2f).toInt()
            val heightInDp = (numberOfLines * HEIGHT_CELL) + ((numberOfLines) * 8)
            galleryAdapter = GalleryAdapter(user.galleryImages)
            itemLayout.recyclerViewGallery.layoutManager = gridLayoutManager
            itemLayout.recyclerViewGallery.adapter = galleryAdapter
            itemLayout.recyclerViewGallery.layoutParams.height =
                heightInDp.dpToPixel(itemView.context)
        }

        fun clear() {
            Glide.with(itemView.context).clear(itemLayout.imageViewAvatar)
        }
    }

    class UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onViewRecycled(holder: DataViewHolder) {
        super.onViewRecycled(holder)
        holder.clear()
    }
}

fun Int.dpToPixel(context: Context): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
).toInt()