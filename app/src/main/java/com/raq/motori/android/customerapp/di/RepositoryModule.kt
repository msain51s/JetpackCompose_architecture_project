package com.raq.motori.android.customerapp.di

import com.raq.motori.android.customerapp.home.data.repository.HomeRepositoryImpl
import com.raq.motori.android.customerapp.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Manoj Sain on 14/01/24.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository
}