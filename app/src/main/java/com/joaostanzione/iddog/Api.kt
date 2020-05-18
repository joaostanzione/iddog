package com.joaostanzione.iddog

import com.joaostanzione.iddog.data.dogs.DogsResponse
import com.joaostanzione.iddog.data.login.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

internal interface Api {

    @FormUrlEncoded
    @POST(ENDPOINT_SIGN_UP)
    suspend fun doLogin(
        @Field(FIELD_EMAIL) email: String
    ): LoginResponse

    @GET(ENDPOINT_FEED)
    suspend fun getDogs(
        @Query(QUERY_CATEGORY) category: String?,
        @Header(HEADER_AUTHORIZATION) auth: String?
    ): DogsResponse

    companion object {
        const val ENDPOINT_SIGN_UP = "signup"
        const val ENDPOINT_FEED = "feed"
        const val FIELD_EMAIL = "email"
        const val HEADER_AUTHORIZATION = "Authorization"
        const val QUERY_CATEGORY = "category"
    }
}
