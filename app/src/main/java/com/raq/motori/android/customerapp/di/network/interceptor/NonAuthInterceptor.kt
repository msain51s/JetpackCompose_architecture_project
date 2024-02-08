package com.raq.motori.android.customerapp.di.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Manoj Sain on 14/01/24.
 */
class NonAuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        return chain.proceed(requestBuilder.build())
    }
}