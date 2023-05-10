package org.android.go.sopt.home.adapter

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
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.android.go.sopt.databinding.HeaderPopPlaylistBinding
import org.android.go.sopt.databinding.ItemPopPlaylistBinding
import org.android.go.sopt.home.data.Pop


class PopAdapter(context: Context) :
    ListAdapter<Pop, RecyclerView.ViewHolder>(diffUtil) {
    private val inflater by lazy { LayoutInflater.from(context) }
    private lateinit var selectionTracker: SelectionTracker<Long>

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int {
        return if(position==0) HEADER
        else ITEM
    }

    fun setSelectionTracker(selectionTracker: SelectionTracker<Long>) {
        this.selectionTracker = selectionTracker
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
       if(position== ITEM){
           PopItemViewHolder(holder as ItemPopPlaylistBinding).onBind(currentList[position])
       }else{
           PopHeaderViewHolder(holder as HeaderPopPlaylistBinding).onBind()
       }
    }


    class PopItemViewHolder(private val binding: ItemPopPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Pop, isActivated: Boolean = false,tracker: SelectionTracker<Long>) {
            binding.ivPopPlaylist.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.tvPopPlaylistTitle.text = data.title
            binding.tvPopPlaylistSinger.text = data.singer
            if(tracker.isSelected(bindingAdapterPosition.toLong())){
                itemView.isActivated = isActivated
            }

        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = bindingAdapterPosition
                override fun getSelectionKey(): Long = itemId
            }

    }

    class PopHeaderViewHolder(private val binding: HeaderPopPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun onBind(tracker: SelectionTracker<Long>){
            if(tracker.isSelected(bindingAdapterPosition.toLong())){
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = bindingAdapterPosition
                override fun getSelectionKey(): Long = itemId
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
        const val HEADER= 0
        const val ITEM = 1
    }

    class PopDetailsLookUp(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
        override fun getItemDetails(motionEvent: MotionEvent): ItemDetails<Long>? {
            val view = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)?:return null
            val viewHolder=recyclerView.getChildViewHolder(view)
            return if (viewHolder.itemViewType== HEADER){
                (viewHolder as PopHeaderViewHolder).getItemDetails()
            }else{
                (viewHolder as PopItemViewHolder).getItemDetails()
            }

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


