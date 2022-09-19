package com.example.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Throwable>()
    val loadingState: LiveData<Boolean>
        get() = loading

    fun getErrorLiveData(): LiveData<Throwable> {
        return errorLiveData
    }

    fun loading(isLoading: Boolean) {
        loading.value = isLoading
    }

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }
}
