package com.raq.motori.android.customerapp.home.domain.repository

import com.raq.motori.android.customerapp.base.ApiState
import com.raq.motori.android.customerapp.home.domain.model.Product
import kotlinx.coroutines.flow.Flow

/**
 * Created by Manoj Sain on 14/01/24.
 */
interface HomeRepository {
    suspend fun getProducts(): Flow<ApiState<List<Product>>>
}