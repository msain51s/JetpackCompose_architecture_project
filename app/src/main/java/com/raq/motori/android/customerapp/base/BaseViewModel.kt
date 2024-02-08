package com.raq.motori.android.customerapp.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Manoj Sain on 11/01/24.
 */
@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {

//    private val _viewState = MutableStateFlow<ApiState<*>>(ApiState.Loading)
//    val viewState get() = _viewState
//
//    open fun<T> updateState(data:T? = null){
//        _viewState
//    }
}