package com.example.marvel_list.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseFragment
import com.example.core.entity.PaginationState
import com.example.core.fragmentTypes.FragmentTypes
import com.example.core.manager.SearchViewManager
import com.example.core.util.PaginationScrollListener
import com.example.domain.entity.MarvelListModel
import com.example.marvel_list.R
import com.example.marvel_list.adapter.MarvelAdapter
import com.example.marvel_list.databinding.FragmentCharactersBinding
import org.koin.android.ext.android.get

class MarvelListFragment : BaseFragment<FragmentCharactersBinding, MarvelListViewModel>() {
    private var currentFragmentType: FragmentTypes = FragmentTypes.Comics
    private var viewModel = get<MarvelListViewModel>()
    private var loading = false
    private var isLastPage = false
    private var clearAllData = false
    private var currentPage: Int = 1
    private var searchQuery: String? = null
    private var characters = ArrayList<MarvelListModel>()
    private var charactersAdapter: MarvelAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCurrentFragment(
            requireContext(),
            findNavController().currentDestination?.label as String
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()


        viewModel.marvelList.observe(viewLifecycleOwner) {
            if (clearAllData) {
                characters.clear()
            }
            characters.addAll(it)
            charactersAdapter?.notifyDataSetChanged()
        }

        viewModel.paginationState.observe(viewLifecycleOwner) {
            when (it) {
                is PaginationState.Loading -> loading = it.isLoading
                is PaginationState.LastPageCalled -> isLastPage = it.isLastPage
                is PaginationState.ClearAllData -> clearAllData = it.clearAllData
            }
        }

        SearchViewManager.searchQuery.observe(viewLifecycleOwner) {
            currentPage = 1
            searchQuery = it
            viewModel.fetchCharacters(currentFragmentType, currentPage, true, searchQuery)
        }

        viewModel.currentFragment.observe(viewLifecycleOwner) {
            searchQuery = null
            currentFragmentType = it

            characters.clear()
            charactersAdapter?.notifyDataSetChanged()

            viewModel.fetchCharacters(currentFragmentType, currentPage, true, searchQuery)
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharactersBinding
        get() = FragmentCharactersBinding::inflate

    override fun getLayoutId(): Int = R.layout.fragment_characters

//    override fun getViewModel(): MarvelListViewModel {
//        return viewModel
//    }

    private fun initRecyclerView() {
        charactersAdapter = MarvelAdapter {
            val uri = Uri.parse("App://DetailsFragment/$it/${currentFragmentType.name}")
            findNavController().navigate(uri)
        }
        charactersAdapter?.submitList(characters)

        val linearLayoutManager = LinearLayoutManager(requireContext())

        binding.charactersList.apply {
            layoutManager = linearLayoutManager
            adapter = charactersAdapter
        }

        binding.charactersList.addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                loading = true
                currentPage += 1
//                viewModel.fetchCharacters(currentFragmentType, currentPage, false, searchQuery)
            }

            override val lastPageCalled: Boolean
                get() = isLastPage
            override val isLoading: Boolean
                get() = loading
        })
    }

    override fun getViewModel(): MarvelListViewModel {
        return viewModel
    }
}
