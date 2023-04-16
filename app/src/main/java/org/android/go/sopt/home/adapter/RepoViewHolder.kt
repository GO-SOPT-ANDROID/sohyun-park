package org.android.go.sopt.home.adapter

import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemGithubRepoBinding
import org.android.go.sopt.home.data.Repo

class RepoViewHolder(private val binding: ItemGithubRepoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: Repo) {
        binding.ivGithubRepo.setImageDrawable(binding.root.context.getDrawable(data.image))
        binding.tvGithubRepoName.text = data.name
        binding.tvGithubRepoAuthor.text = data.author
    }
}