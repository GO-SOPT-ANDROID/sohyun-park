package org.android.go.sopt.presentation.main.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.data.dto.ResponseListUsersDto
import org.android.go.sopt.databinding.ItemUserBinding
import org.android.go.sopt.util.ItemDiffCallback

class UserAdapter(context: Context) :
    ListAdapter<ResponseListUsersDto.ListUsersData, UserAdapter.UserItemViewHolder>(ItemDiffCallback<ResponseListUsersDto.ListUsersData>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old == new }
    )) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.onBind(
            getItem(position) as ResponseListUsersDto.ListUsersData
        )
    }


    class UserItemViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: ResponseListUsersDto.ListUsersData) {
            with(binding) {
                Glide.with(root).load(data.avatar).into(ivItemUserAvatar)
                tvItemUserFirstName.text = data.firstName
                tvItemUserEmail.text = data.email
            }

        }

    }


}