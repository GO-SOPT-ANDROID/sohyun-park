package org.android.go.sopt.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.HeaderPopPlaylistBinding

class PopHeaderAdapter(context: Context) :
    RecyclerView.Adapter<PopHeaderAdapter.RepoHeaderViewHolder>() {

    class RepoHeaderViewHolder(private val binding: HeaderPopPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHeaderViewHolder {
        val binding =
            HeaderPopPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoHeaderViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: RepoHeaderViewHolder, position: Int) {}
}