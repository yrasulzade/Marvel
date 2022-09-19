package com.example.feature_characters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.feature_characters.databinding.ItemCharacterBinding

class CharactersAdapter(val itemClick: () -> Unit) :
    ListAdapter<String, CharactersViewHolder>(CharacterDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {

        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CharactersViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, index: Int) {
        val test = getItem(index)
        holder.binding.textview.text = test

        holder.binding.item.setOnClickListener {
            itemClick.invoke()
        }
    }
}
