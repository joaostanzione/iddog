package com.joaostanzione.iddog.ui.login

import androidx.lifecycle.Observer
import com.joaostanzione.iddog.data.login.LoginResponse
import com.joaostanzione.iddog.domain.login.LoginUseCase
import com.joaostanzione.iddog.BaseUnitTest
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertTrue
import org.junit.Test

internal class LoginViewModelTest : BaseUnitTest() {

    @MockK
    lateinit var loginUseCase: LoginUseCase

    private lateinit var viewModel: LoginViewModel

    @Test
    fun onLoginClick_OnSuccess_ShouldCallSuccessCallback() {
        val loginData: LoginResponse = getMockJson(LOGIN_JSON)
        coEvery { loginUseCase.doLogin(any()) } returns loginData.user?.token
        initViewModel()

        val stateObserver = mockk<Observer<LoginViewModel.LoginState>>(relaxed = true)

        viewModel.onLoginClick("test@test.com")

        viewModel.states.observeForever(stateObserver)

        verify { stateObserver.onChanged(any()) }

        assertTrue(viewModel.states.value is LoginViewModel.LoginState.Success)
    }

    @Test
    fun onLoginClick_OnError_ShouldCallErrorCallback() {
        coEvery { loginUseCase.doLogin(any()) } throws Exception()
        initViewModel()

        val stateObserver = mockk<Observer<LoginViewModel.LoginState>>(relaxed = true)

        viewModel.onLoginClick("test@test.com")

        viewModel.states.observeForever(stateObserver)

        verify { stateObserver.onChanged(any()) }

        assertTrue(viewModel.states.value is LoginViewModel.LoginState.Error)
    }

    private fun initViewModel() {
        viewModel = LoginViewModel(
            Dispatchers.Unconfined,
            loginUseCase
        )
    }

    companion object {
        const val LOGIN_JSON = "Login.json"
    }
}
