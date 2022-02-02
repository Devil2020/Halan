package com.example.halanchallenge.domain.usecase

import com.example.halanchallenge.LoginData
import com.example.halanchallenge.ProductsData
import com.example.halanchallenge.data.local.ILocalGateway
import com.example.halanchallenge.data.remote.IRemoteGateway
import com.example.halanchallenge.data.repository.ProductRepository
import com.example.halanchallenge.data.repository.UserRepository
import com.example.halanchallenge.domain.entities.product.ProductRequest
import com.example.halanchallenge.domain.repository.IProductRepository
import com.example.halanchallenge.domain.repository.IUserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UseCasesKtTest {

    var userRepository: IUserRepository? = null
    var productsRepository: IProductRepository? = null
    val remoteIRemoteGateway: IRemoteGateway = mockk()
    val localIRemoteGateway: ILocalGateway = mockk()

    @Before
    fun setup() {
        userRepository = UserRepository(remoteIRemoteGateway, localIRemoteGateway)
        productsRepository = ProductRepository(remoteIRemoteGateway)
    }

    @Test
    fun `When Try to Login With Valid Creditional Data Then Must Return Success Status With New Login Response`() {

        coEvery {
            remoteIRemoteGateway.login(
                LoginData.Input.ValidCriteria
            )
        } returns LoginData.Output.SuccessResult

        val response = executeLoginUserUseCase(userRepository!!, LoginData.Input.ValidCriteria)
        runBlocking {
            val last = response.last()
            Assert.assertTrue(last.loginResponse != null )
        }


    }

    @Test
    fun `When Try to Login With Not Valid Creditional Data Then Must Return Error Status`() {

        coEvery {
            remoteIRemoteGateway.login(
                LoginData.Input.NotPasswordValidCriteria
            )
        } throws Exception("missing password")

        val response = executeLoginUserUseCase(userRepository!!, LoginData.Input.ValidCriteria)
        runBlocking {
            val last = response.last()
            Assert.assertTrue(last.error != null )
        }

    }

    @Test
    fun `When Get Products With Valid Token Then Must Return Success Status With List Of Products`() {

        coEvery {
            remoteIRemoteGateway.getProducts(
                ProductRequest(ProductsData.Input.ValidToken)
            )
        } returns ProductsData.Output.SuccessProducts

        val response =
            executeGetProductsUseCase(productsRepository!!, ProductsData.Input.ValidToken)
        runBlocking {
            val last = response.last()
            Assert.assertTrue(last.productsResponse != null )
        }

    }

    @Test
    fun `When Get Products With Not Valid Token Then Must Return Error Status `() {

        coEvery {
            remoteIRemoteGateway.getProducts(
                ProductRequest(ProductsData.Input.ExpiredToken)
            )
        } throws Exception("Token expired")

        val response =
            executeGetProductsUseCase(productsRepository!!, ProductsData.Input.ValidToken)
        runBlocking {
            val last = response.last()
            Assert.assertTrue(last.error != null )
        }

    }

    @After
    fun terminate() {
        userRepository = null
        productsRepository = null
    }

}