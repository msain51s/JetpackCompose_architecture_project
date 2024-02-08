package com.raq.motori.android.customerapp.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raq.motori.android.customerapp.base.ApiState
import com.raq.motori.android.customerapp.home.domain.repository.HomeRepository
import com.raq.motori.android.customerapp.home.presentation.HomeViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Manoj Sain on 10/01/24.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {
    private val _viewState = MutableStateFlow(HomeViewStates())
    val viewState = _viewState.asStateFlow()


    fun getProducts(){
        viewModelScope.launch {

            homeRepository.getProducts().collect{ apiState ->
                when(apiState){
                    is ApiState.Loading -> _viewState.update {
                        it.copy(isLoading = true)
                    }
                    is ApiState.Success ->_viewState.update {
                        it.copy(products = apiState.data)
                    }
                    is ApiState.Error ->_viewState.update {
                        it.copy(error = apiState.msg)
                    }
                }

            }

            // dismissing the loader
            _viewState.update {
                it.copy(isLoading = false)
            }

        }
    }
}