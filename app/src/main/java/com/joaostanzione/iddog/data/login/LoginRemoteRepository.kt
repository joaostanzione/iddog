package com.joaostanzione.iddog.data.login

import com.joaostanzione.iddog.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class LoginRemoteRepository(
    private val api: Api
) : LoginRepository {

    override suspend fun doLogin(email: String): LoginResponse = withContext(Dispatchers.IO) {
        api.doLogin(email)
    }
}
