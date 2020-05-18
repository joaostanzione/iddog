package com.joaostanzione.iddog

import com.joaostanzione.iddog.data.login.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface Api {

    @FormUrlEncoded
    @POST(ENDPOINT_SIGN_UP)
    suspend fun doLogin(
        @Field(FIELD_EMAIL) email: String
    ): LoginResponse

    companion object {
        const val ENDPOINT_SIGN_UP = "signup"
        const val FIELD_EMAIL = "email"
    }
}
