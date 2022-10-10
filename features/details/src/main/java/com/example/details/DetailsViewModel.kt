package com.example.details

import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.domain.entity.MarvelListModel
import com.example.domain.usecase.MarvelDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class DetailsViewModel constructor(private val marvelDetailsUseCase: MarvelDetailsUseCase) :
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
