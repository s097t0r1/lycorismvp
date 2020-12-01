package com.s097t0r1.lycorismvp.ui

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.s097t0r1.lycorismvp.R
import com.s097t0r1.lycorismvp.model.Photo

class PhotoAdapter(val itemClickListener: ItemClickListener) : ListAdapter<Photo, PhotoAdapter.PhotoViewHolder>(PhotoItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo, itemClickListener)
    }

    class PhotoViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageViewPhoto = itemView.findViewById<ImageView>(R.id.imageView_photo)
        val itemLayout = itemView.findViewById<ConstraintLayout>(R.id.constraintLayout_itemLayout)

        fun bind(photo: Photo, clickListener: ItemClickListener) {
            Glide.with(imageViewPhoto.context)
                .load(photo.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_photo_24)
                .error(R.drawable.ic_error_outline_24)
                .into(imageViewPhoto)

            itemLayout.setOnClickListener {
                clickListener.onClick(photo.id)
            }
        }

        companion object {
            fun from(parent: ViewGroup): PhotoViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val itemView = inflater.inflate(R.layout.item_photo, parent, false)
                return PhotoViewHolder(itemView)
            }
        }
    }

}

class PhotoItemDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return (oldItem.description == newItem.description && oldItem.imageUrl == newItem.imageUrl)
    }

}

interface ItemClickListener {
    fun onClick(id: String)
}