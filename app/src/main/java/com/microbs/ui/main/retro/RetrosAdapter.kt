package com.microbs.ui.main.retro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.microbs.R
import com.microbs.databinding.ItemRetroBinding
import com.microbs.model.Retro

class RetrosAdapter : ListAdapter<Retro, RetrosAdapter.RetroHolder>(object :
    DiffUtil.ItemCallback<Retro>() {
    override fun areItemsTheSame(
        oldItem: Retro,
        newItem: Retro
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Retro,
        newItem: Retro
    ): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetroHolder {
        return RetroHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_retro, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RetroHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RetroHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Retro) {
            val binding = ItemRetroBinding.bind(itemView)

            binding.retroTextView.text = item.toString()
        }
    }
}
