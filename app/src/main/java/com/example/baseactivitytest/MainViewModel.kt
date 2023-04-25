package com.example.baseactivitytest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  author : wsy
 *  date   : 2023/1/3
 *  desc   :
 */
class MainViewModel : ViewModel() {
    private val _uiState = MutableLiveData<UiModel>()
    val uiState: LiveData<UiModel>
        get() = _uiState

    private fun emitUIState(
        showLoading: Boolean = false,
        showError: String? = null,
        showSuccess: String? = null,
        showEnd: Boolean = false, // 加载更多
        isRefresh: Boolean = false // 刷新
    ) {
        val uiModel = UiModel(showLoading, showError, showSuccess, showEnd, isRefresh)
        _uiState.postValue(uiModel)
    }

    data class UiModel(
        var showLoading: Boolean = false,
        var showError: String? = null,
        var showSuccess: String? = null,
        var showEnd: Boolean = false, // 加载更多
        var isRefresh: Boolean = false // 刷新
    )

    fun requestData(){
        emitUIState(showSuccess = "hello man")
    }
}