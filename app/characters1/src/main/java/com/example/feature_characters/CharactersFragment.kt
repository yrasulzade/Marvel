package com.example.feature_characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feature_characters.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentCharactersBinding.inflate(LayoutInflater.from(requireContext()))

        val charactersAdapter = CharactersAdapter {
            findNavController().navigate(com.example.details.R.id.details_navigation)
        }

        val list = listOf(
            "One",
            "Two",
            "Three",
            "One",
            "Two",
            "Three",
            "One",
            "Two",
            "Three",
            "One",
            "Two",
            "Three",
            "Three",
            "One",
            "Two",
            "Three",
            "One",
            "Two",
            "Three"
        )

        binding.charactersList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = charactersAdapter
        }
        charactersAdapter.submitList(list)


        return binding.root
    }
}
