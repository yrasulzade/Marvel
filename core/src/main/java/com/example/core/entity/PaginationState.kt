package com.example.core.entity

sealed class PaginationState {
    data class Loading(val isLoading: Boolean) : PaginationState()
    data class LastPageCalled(val isLastPage: Boolean) : PaginationState()
    data class ClearAllData(val clearAllData: Boolean) : PaginationState()
}