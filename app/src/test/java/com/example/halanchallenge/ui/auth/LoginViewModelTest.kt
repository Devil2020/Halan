package com.example.halanchallenge.ui.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.halanchallenge.LoginData
import com.example.halanchallenge.TestCoroutineRule
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

class LoginViewModelTest {

    var loginViewModel: LoginViewModel? = null
    private val userRepository: IUserRepository = mockk()
    private val intentFlow = MutableSharedFlow<Intent>()

    @ExperimentalCoroutinesApi
    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        loginViewModel = LoginViewModel(userRepository)
    }

    @FlowPreview
    @Test
    fun `Test MVI Intents Handling To LogIn User With Valid LoginRequest`() {
        testCoroutineRule.launch {
            loginViewModel?.processIntents(intentFlow)
            coEvery { userRepository.loginUser(LoginData.Input.ValidCriteria) } returns LoginData.Output.SuccessResult
            intentFlow.tryEmit(Intent.Login)
            val response = loginViewModel?.getStatus()?.last()
            Assert.assertTrue(response is State.Success<*>)
        }

    }

    @FlowPreview
    @Test
    fun `Test MVI Intents Handling To LogIn User With Not Valid LoginRequest`() {
        testCoroutineRule.launch {
            loginViewModel?.processIntents(intentFlow)
            coEvery { userRepository.loginUser(LoginData.Input.NotNameValidCriteria) } throws Exception(
                LoginData.Output.ErrorUserNameResult.message
            )
            intentFlow.tryEmit(Intent.Login)
            val response = loginViewModel?.getStatus()?.last()
            Assert.assertTrue(response is State.Error)
        }

    }

    @ExperimentalCoroutinesApi
    @After
    fun terminate() {
        loginViewModel = null
    }
}