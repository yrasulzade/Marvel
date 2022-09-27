package com.example.marvel_list.ui

import android.content.Context
import com.example.core.base.BaseViewModel
import com.example.core.entity.PaginationState
import com.example.core.fragmentTypes.FragmentTypes
import com.example.core.util.SingleLiveEvent
import com.example.core.util.Utils.ifNonNull
import com.example.domain.entity.MarvelListModel
import com.example.domain.usecase.MarvelListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MarvelListViewModel @Inject constructor(private val marvelListUseCase: MarvelListUseCase) :
    BaseViewModel() {
    private val _paginationState = SingleLiveEvent<PaginationState>()
    val paginationState = _paginationState

    private val _marvelList = SingleLiveEvent<List<MarvelListModel>>()
    val marvelList = _marvelList

    private val _currentFragment = SingleLiveEvent<FragmentTypes>()
    val currentFragment = _currentFragment

    fun fetchCharacters(
        type: FragmentTypes,
        page: Int,
        clearAllData: Boolean,
        query: String? = null
    ) {

        launchOnUI {
            flow {
                emit(marvelListUseCase.execute(type, page, query))
            }
                .flowOn(Dispatchers.IO)
                .onStart { loading(true) }
                .onCompletion {
                    loading(false)
                    _paginationState.value = PaginationState.Loading(false)
                }
                .catch { errorLiveData.value = it }
                .collect {
                    it.first?.ifNonNull { limit ->
                        _paginationState.value = PaginationState.LastPageCalled(page == limit)
                        _paginationState.value = PaginationState.ClearAllData(clearAllData)
                    }

                    it.second.ifNonNull { list ->
                        _marvelList.postValue(list)
                    }
                }
        }
    }

    fun getCurrentFragment(context: Context, fragmentLabel: String) {
        when (fragmentLabel) {
            context.getString(com.example.core.R.string.characters) -> _currentFragment.value =
                FragmentTypes.Characters
            context.getString(com.example.core.R.string.comics) -> _currentFragment.value =
                FragmentTypes.Comics
            context.getString(com.example.core.R.string.creators) -> _currentFragment.value =
                FragmentTypes.Creators
            context.getString(com.example.core.R.string.series) -> _currentFragment.value =
                FragmentTypes.Series
        }
    }
}
