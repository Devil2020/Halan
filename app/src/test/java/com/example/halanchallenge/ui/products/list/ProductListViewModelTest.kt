package com.example.halanchallenge.ui.products.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.halanchallenge.ProductsData
import com.example.halanchallenge.TestCoroutineRule
import com.example.halanchallenge.domain.entities.product.ProductRequest
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository
import com.example.halanchallenge.ui.entities.Intent
import com.example.halanchallenge.ui.entities.State
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import org.junit.*

class ProductListViewModelTest {

    var productsViewModel: ProductListViewModel? = null
    private val userRepository: IUserRepository = mockk()
    private val productsRepository: IProductRepository = mockk()
    private val intentFlow = MutableSharedFlow<Intent>()

    @ExperimentalCoroutinesApi
    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        productsViewModel = ProductListViewModel(userRepository, productsRepository)
    }

    @FlowPreview
    @Test
    fun `Test MVI Intents Handling To Get Product List User With Valid Token`() {
        testCoroutineRule.launch {
            productsViewModel?.processIntents(intentFlow)
            coEvery { productsRepository.loadProductsList(ProductRequest(ProductsData.Input.ValidToken)) } returns ProductsData.Output.SuccessProducts
            intentFlow.tryEmit(Intent.GetProducts)
            val response = productsViewModel?.getStatus()?.last()
            Assert.assertTrue(response is State.Success<*>)
        }

    }

    @FlowPreview
    @Test
        fun `Test MVI Intents Handling To Load ProductList With Not Valid Token`() {
        testCoroutineRule.launch {
            productsViewModel?.processIntents(intentFlow)
            coEvery { productsRepository.loadProductsList(ProductRequest(ProductsData.Input.ExpiredToken)) } throws Exception(
                ProductsData.Output.ExpiredAuthorizedUser.message
            )
            intentFlow.tryEmit(Intent.GetProducts)
            val response = productsViewModel?.getStatus()?.last()
            Assert.assertTrue(response is State.Error)
        }

    }

    @ExperimentalCoroutinesApi
    @After
    fun terminate() {
        productsViewModel = null
    }
}