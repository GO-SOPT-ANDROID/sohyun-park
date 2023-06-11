package org.android.go.sopt.presentation.main.gallery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemPagerBinding
import org.android.go.sopt.presentation.main.gallery.model.Gallery
import org.android.go.sopt.util.ItemDiffCallback

class GalleryAdapter(context: Context) :
    ListAdapter<Gallery, GalleryAdapter.GalleryViewHolder>(
        ItemDiffCallback<Gallery>(onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old == new })
    ) {

    class GalleryViewHolder(private val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Gallery) {
            binding.ivItemPager.setImageDrawable(binding.root.context.getDrawable(data.image))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


}