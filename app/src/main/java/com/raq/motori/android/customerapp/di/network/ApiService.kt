package com.raq.motori.android.customerapp.di.network

import com.raq.motori.android.customerapp.home.domain.model.Product
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Manoj Sain on 14/01/24.
 */
interface ApiService {

    @GET("/products")
    suspend fun getProduct(): Response<List<Product>>
}