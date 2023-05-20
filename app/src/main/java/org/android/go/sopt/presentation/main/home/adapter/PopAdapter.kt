package org.android.go.sopt.presentation.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.R
import org.android.go.sopt.databinding.HeaderPopPlaylistBinding
import org.android.go.sopt.databinding.ItemPopPlaylistBinding
import org.android.go.sopt.presentation.main.home.model.Pop
import org.android.go.sopt.showToast


class PopAdapter(context: Context) :
    ListAdapter<Pop, RecyclerView.ViewHolder>(diffUtil) {
    private lateinit var selectionTracker: SelectionTracker<Long>

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER
        else ITEM
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER) {
            val binding = HeaderPopPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            PopHeaderViewHolder(binding)
        } else {
            val binding =
                ItemPopPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PopItemViewHolder(binding)
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == HEADER) {
            (holder as PopHeaderViewHolder).run {
                onBind(currentList[position], selectionTracker)
            }

        } else {
            (holder as PopItemViewHolder).run {
                onBind(currentList[position], selectionTracker)
            }
        }
    }


    class PopItemViewHolder(private val binding: ItemPopPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Pop, tracker: SelectionTracker<Long>) {
            binding.ivPopPlaylist.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.tvPopPlaylistTitle.text = data.title
            binding.tvPopPlaylistSinger.text = data.singer
            binding.root.isActivated = tracker.isSelected(itemId)
            if (tracker.isSelected(bindingAdapterPosition.toLong())) {
                binding.root.setBackgroundResource(R.color.yellow_300)
            } else {
                binding.root.setBackgroundResource(R.color.white)
            }

        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = bindingAdapterPosition
                override fun getSelectionKey(): Long = itemId
            }

    }

    class PopHeaderViewHolder(private val binding: HeaderPopPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: Pop, tracker: SelectionTracker<Long>) {
            binding.ivHeaderIcon.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.tvHeaderTitle.text = data.title
            if (tracker.isSelected(bindingAdapterPosition.toLong())) {
                binding.root.showToast("Title")
            } else {
                binding.root.setBackgroundResource(R.color.white)
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = bindingAdapterPosition
                override fun getSelectionKey(): Long = itemId
            }

    }

    fun setSelectionTracker(selectionTracker: SelectionTracker<Long>) {
        this.selectionTracker = selectionTracker
    }

    class PopDetailsLookUp(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
        override fun getItemDetails(motionEvent: MotionEvent): ItemDetails<Long>? {
            val view = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y) ?: return null
            val viewHolder = recyclerView.getChildViewHolder(view)
            return if (viewHolder.itemViewType == HEADER) {
                (viewHolder as PopHeaderViewHolder).getItemDetails()
            } else {
                (viewHolder as PopItemViewHolder).getItemDetails()
            }

        }
    }

    class SelectionItemKeyProvider(private val recyclerView: RecyclerView) :
        ItemKeyProvider<Long>(SCOPE_MAPPED) {

        override fun getKey(position: Int): Long {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
            return viewHolder?.itemId ?: RecyclerView.NO_ID
        }

        override fun getPosition(key: Long): Int {
            val viewHolder = recyclerView.findViewHolderForItemId(key)
            return viewHolder?.layoutPosition ?: RecyclerView.NO_POSITION
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
        const val HEADER = 0
        const val ITEM = 1
    }


}


