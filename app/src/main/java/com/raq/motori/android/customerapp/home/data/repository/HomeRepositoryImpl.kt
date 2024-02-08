package com.raq.motori.android.customerapp.home.data.repository

import com.raq.motori.android.customerapp.base.ApiState
import com.raq.motori.android.customerapp.base.toResultFlow
import com.raq.motori.android.customerapp.home.domain.model.Product
import com.raq.motori.android.customerapp.home.domain.repository.HomeRepository
import com.raq.motori.android.customerapp.di.network.qualifier.NonAuthApiService
import com.raq.motori.android.customerapp.di.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Manoj Sain on 14/01/24.
 */
class HomeRepositoryImpl @Inject constructor(
    @NonAuthApiService private val apiService: ApiService
): HomeRepository {
    override suspend fun getProducts(): Flow<ApiState<List<Product>>> {
        return toResultFlow { apiService.getProduct() }
    }
}