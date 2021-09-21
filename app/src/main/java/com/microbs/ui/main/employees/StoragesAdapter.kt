package com.microbs.ui.main.employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.microbs.R
import com.microbs.databinding.ItemStorageBinding
import com.microbs.model.Storage

class StoragesAdapter : ListAdapter<Storage, StoragesAdapter.StorageHolder>(object :
    DiffUtil.ItemCallback<Storage>() {
    override fun areItemsTheSame(
        oldItem: Storage,
        newItem: Storage
    ): Boolean {
        return oldItem.storageId == newItem.storageId
    }

    override fun areContentsTheSame(
        oldItem: Storage,
        newItem: Storage
    ): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageHolder {
        return StorageHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_storage, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StorageHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StorageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Storage) {
            val binding = ItemStorageBinding.bind(itemView)

            binding.storageTextView.text = item.toString()
        }
    }
}
