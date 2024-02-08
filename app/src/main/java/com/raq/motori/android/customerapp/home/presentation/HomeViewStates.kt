package com.raq.motori.android.customerapp.home.presentation

import com.raq.motori.android.customerapp.home.domain.model.Product

/**
 * Created by Manoj Sain on 14/01/24.
 */
data class HomeViewStates(
    val isLoading:Boolean = false,
    val products:List<Product> = emptyList(),
    val error:String? = null
)
