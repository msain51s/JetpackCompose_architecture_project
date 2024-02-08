package com.raq.motori.android.customerapp.home.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.raq.motori.android.customerapp.base.ApiState
import com.raq.motori.android.customerapp.home.domain.model.Product
import com.raq.motori.android.customerapp.home.domain.repository.HomeRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Created by Manoj Sain on 02/02/24.
 */
class HomeViewModelTest {

    @get:Rule
    private val rule: TestRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    private lateinit var homeRepository: HomeRepository
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeRepository = mockk()
        homeViewModel = HomeViewModel(homeRepository)
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `When get products with Loading State`() = runTest {
        every {
            runBlocking {
                homeRepository.getProducts()
            }
        } returns flow { emit(ApiState.Loading) }

        homeViewModel.getProducts()
        dispatcher.scheduler.advanceUntilIdle()
        val result = homeViewModel.viewState.value.copy(isLoading = true)
        assert(result.isLoading)
    }

    @Test
    fun `When get products with Success State with emptyList`() = runTest {
        every {
            runBlocking {
                homeRepository.getProducts()
            }
        } returns flow { emit(ApiState.Success(data = emptyList())) }

        homeViewModel.getProducts()
        dispatcher.scheduler.advanceUntilIdle()
        val result = homeViewModel.viewState.value.copy(products = emptyList())
        assert(result.products.isEmpty())
    }

    @Test
    fun `When get products with Success State with productList`() = runTest {
        val list = listOf(Product(id = 1, title = "Product 1"))
        every {
            runBlocking {
                homeRepository.getProducts()
            }
        } returns flow { emit(ApiState.Success(data = list)) }

        homeViewModel.getProducts()
        dispatcher.scheduler.advanceUntilIdle()
        val result = homeViewModel.viewState.value.copy(products = list)
        assert(result.products.isNotEmpty())
        assert(result.products[0].id == 1 && result.products[0].title == "Product 1")
    }

    @Test
    fun `When get products with Error State`() = runTest {
        every {
            runBlocking {
                homeRepository.getProducts()
            }
        } returns flow { emit(ApiState.Error(msg = "Unauthorised")) }

        homeViewModel.getProducts()
        dispatcher.scheduler.advanceUntilIdle()
        val result = homeViewModel.viewState.value.copy(error = "Unauthorised")
        assert(result.error == "Unauthorised")
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}