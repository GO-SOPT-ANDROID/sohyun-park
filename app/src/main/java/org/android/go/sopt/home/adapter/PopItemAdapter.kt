package org.android.go.sopt.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemPopPlaylistBinding
import org.android.go.sopt.home.data.Pop

class PopItemAdapter(context: Context) :
    ListAdapter<Pop, PopItemAdapter.RepoViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }

    class RepoViewHolder(private val binding: ItemPopPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Pop) {
            binding.ivPopPlaylist.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.tvPopPlaylistTitle.text = data.title
            binding.tvPopPlaylistSinger.text = data.singer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemPopPlaylistBinding.inflate(inflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RepoViewHolder,
        position: Int
    ) {
        holder.onBind(currentList[position])
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Pop>() {
            override fun areItemsTheSame(oldItem: Pop, newItem: Pop): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pop, newItem: Pop): Boolean {
                return oldItem == newItem
            }

        }
    }

}