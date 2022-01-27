package com.example.halanchallenge.remote

import com.example.halanchallenge.LoginData
import com.example.halanchallenge.data.remote.IRemoteGateway
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

class RetrofitRemoteGatewayTest {

    var remoteGateway: IRemoteGateway? = null

    @Before
    fun setUp() {
        remoteGateway = RetrofitRemoteGateway()
    }

    @Test
    fun testLoginWithValidCreditionalThenReturnSuccessData() {
        runBlocking {
            // Given
            val request = LoginData.Input.ValidCriteria
            // When
            val response = remoteGateway?.login(request)
            //Then
            Assert.assertTrue(response?.status.equals("OK"))
        }
    }

    @Test(expected = HttpException::class)
    fun testLoginWithInValidUserNameCreditionalThenReturnErrorData() {
        runBlocking {
            // Given
            val request = LoginData.Input.NotNameValidCriteria
            // When
            val response = remoteGateway?.login(request)
            //Then
            Assert.assertTrue(response?.equals(LoginData.Output.ErrorUserNameResult)!!)
        }
    }

    @Test(expected = HttpException::class)
    fun testLoginWithInValidPasswordCreditionalThenReturnErrorData() {
        runBlocking {
            // Given
            val request = LoginData.Input.NotPasswordValidCriteria
            // When
            val response = remoteGateway?.login(request)
            //Then
            Assert.assertTrue(response?.equals(LoginData.Output.ErrorPasswordResult)!!)
        }
    }

    @Test(expected = HttpException::class)
    fun testLoginWithInValidPasswordAndUserNameCreditionalThenReturnErrorData() {
        runBlocking {
            // Given
            val request = LoginData.Input.BothUsernameAndPasswordNotValid
            // When
            val response = remoteGateway?.login(request)
            //Then
            Assert.assertTrue(response?.equals(LoginData.Output.BothCredentialErrorResult)!!)
        }
    }


    @Test
    fun testGetProducts() {
    }

    @After
    fun tearDown() {
        remoteGateway = null
    }

}