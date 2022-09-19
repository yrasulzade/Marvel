package com.example.feature_characters

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_characters.databinding.ItemCharacterBinding

class CharactersViewHolder(
    val binding: ItemCharacterBinding,
    val context: Context
) : RecyclerView.ViewHolder(binding.root)

object CharacterDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}
