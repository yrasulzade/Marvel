package com.example.details

import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.domain.entity.MarvelListModel
import com.example.domain.usecase.MarvelDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val marvelDetailsUseCase: MarvelDetailsUseCase) :
    BaseViewModel() {
    private val _details = MutableLiveData<MarvelListModel>()
    val details = _details

    fun fetchCharacterDetails(id: Int, type: String) {

        launchOnUI {
            flow {
                emit(marvelDetailsUseCase.execute(id, type))
            }
                .flowOn(Dispatchers.IO)
                .onStart { loading(true) }
                .onCompletion { loading(false) }
                .catch { errorLiveData.value = it }
                .collect {
                    it?.let {
                        _details.value = it
                    }
                }
        }
    }
}
