package org.android.go.sopt.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemPopPlaylistBinding
import org.android.go.sopt.home.data.Pop


class PopItemAdapter(context: Context) :
    ListAdapter<Pop, PopItemAdapter.PopViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }
    private lateinit var selectionTracker: SelectionTracker<Long>

    init {
        setHasStableIds(true)
    }

    fun setSelectionTracker(selectionTracker: SelectionTracker<Long>) {
        this.selectionTracker = selectionTracker
    }


    override fun getItemId(position: Int): Long = position.toLong()


    class PopViewHolder(private val binding: ItemPopPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Pop, isActivated: Boolean = false) {
            binding.ivPopPlaylist.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.tvPopPlaylistTitle.text = data.title
            binding.tvPopPlaylistSinger.text = data.singer
            itemView.isActivated = isActivated
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = bindingAdapterPosition
                override fun getSelectionKey(): Long = itemId
            }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopViewHolder {
        val binding = ItemPopPlaylistBinding.inflate(inflater, parent, false)
        return PopViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PopViewHolder,
        position: Int
    ) {
        selectionTracker?.let {
            holder.onBind(currentList[position], it.isSelected((position.toLong())))
        }
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

    class PopDetailsLookUp(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
        override fun getItemDetails(motionEvent: MotionEvent): ItemDetails<Long>? {
            val view = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)
            if (view != null) {
                return (recyclerView.getChildViewHolder(view) as PopItemAdapter.PopViewHolder)
                    .getItemDetails()
            }

            return null

        }
    }

    class RecyclerViewIdKeyProvider(private val recyclerView: RecyclerView) :
        ItemKeyProvider<Long>(ItemKeyProvider.SCOPE_MAPPED) {

        override fun getKey(position: Int): Long? {
            return recyclerView.adapter?.getItemId(position)
                ?: throw IllegalStateException("RecyclerView adapter is not set!")
        }

        override fun getPosition(key: Long): Int {
            val viewHolder = recyclerView.findViewHolderForItemId(key)
            return viewHolder?.layoutPosition ?: RecyclerView.NO_POSITION
        }

    }


}


