package com.joaostanzione.iddog.domain.login

import com.joaostanzione.iddog.data.login.LoginRepository

internal class LoginUseCase(private val loginRepository: LoginRepository) {
    suspend fun doLogin(email: String): String? = loginRepository.doLogin(email).user?.token
}
