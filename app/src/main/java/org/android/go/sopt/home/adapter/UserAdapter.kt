package org.android.go.sopt.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.databinding.ItemUserBinding
import org.android.go.sopt.home.ResponseListUsersDto

class UserAdapter(context: Context) :
    ListAdapter<ResponseListUsersDto.ListUsersData, UserAdapter.UserItemViewHolder>(diffUtil) {


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


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseListUsersDto.ListUsersData>() {
            override fun areItemsTheSame(
                oldItem: ResponseListUsersDto.ListUsersData,
                newItem: ResponseListUsersDto.ListUsersData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseListUsersDto.ListUsersData,
                newItem: ResponseListUsersDto.ListUsersData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


}