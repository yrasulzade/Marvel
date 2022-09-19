package com.example.core.manager

import com.example.core.util.SingleLiveEvent

object SearchViewManager {
    val searchQuery = SingleLiveEvent<String?>()
}
