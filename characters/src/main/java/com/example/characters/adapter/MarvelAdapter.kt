package com.example.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.characters.databinding.ItemCharacterBinding
import com.example.domain.entity.MarvelListModel

class MarvelAdapter(private val itemClick: (Int) -> Unit) :
    ListAdapter<MarvelListModel, MarvelViewHolder>(CharacterDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelViewHolder {

        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MarvelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarvelViewHolder, index: Int) {
        val item = getItem(index)
        holder.binding.textview.text = item.title

        holder.binding.item.setOnClickListener {
            itemClick.invoke(item.id)
        }

        holder.binding.image.load(item.thumbnail)
    }
}
