package com.joaostanzione.iddog.data.login

internal interface LoginRepository {

    suspend fun doLogin(email: String): LoginResponse
}
