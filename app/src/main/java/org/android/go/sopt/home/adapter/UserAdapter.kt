package org.android.go.sopt.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.databinding.ItemUserBinding
import org.android.go.sopt.home.data.User

class UserAdapter(context: Context) :
    ListAdapter<User, UserAdapter.UserItemViewHolder>(diffUtil) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.onBind(
            getItem(position) as User
        )
    }


    class UserItemViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: User) {
            with(binding) {
                Glide.with(root).load(data.avatar).into(ivItemUserAvatar)
                tvItemUserFirstName.text = data.firstName
                tvItemUserEmail.text = data.email
            }

        }

    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

        }
    }


}