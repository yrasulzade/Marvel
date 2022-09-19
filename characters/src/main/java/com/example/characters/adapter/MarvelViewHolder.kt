package com.example.characters.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.characters.databinding.ItemCharacterBinding
import com.example.domain.entity.MarvelListModel

class MarvelViewHolder(
    val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root)

object CharacterDiffCallback : DiffUtil.ItemCallback<MarvelListModel>() {
    override fun areItemsTheSame(oldItem: MarvelListModel, newItem: MarvelListModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MarvelListModel, newItem: MarvelListModel): Boolean {
        return oldItem == newItem
    }
}
