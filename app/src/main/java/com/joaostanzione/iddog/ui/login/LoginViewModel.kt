package com.joaostanzione.iddog.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joaostanzione.iddog.domain.login.LoginUseCase
import com.joaostanzione.iddog.ui.BaseViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.launch

internal class LoginViewModel(
    coroutineContext: CoroutineContext,
    private val loginUseCase: LoginUseCase
) : BaseViewModel(coroutineContext) {

    private val _states = MutableLiveData<LoginState>()
    val states: LiveData<LoginState>
        get() = _states

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    fun onLoginClick(email: String) {
        uiScope.launch {
            _showLoading.value = true
            try {
                loginUseCase.doLogin(email)?.let {
                    _states.value = LoginState.Success(it)
                }
            } catch (error: Exception) {
                _states.value = LoginState.Error(error)
            } finally {
                _showLoading.value = false
            }
        }
    }

    sealed class LoginState {
        data class Success(val token: String) : LoginState()
        data class Error(val exception: Exception) : LoginState()
    }
}
