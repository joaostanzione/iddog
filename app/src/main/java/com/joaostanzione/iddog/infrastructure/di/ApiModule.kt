package com.joaostanzione.iddog.infrastructure.di

import com.google.gson.GsonBuilder
import com.joaostanzione.iddog.Api
import com.joaostanzione.iddog.BuildConfig
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TIMEOUT = 30L

val apiModule = module {

    factory { providesOkHttpClient() }
    single {
        createWebService<Api>(
            okHttpClient = get(),
            url = BuildConfig.BASE_URL
        )
    }
}

fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS).apply {
            HttpLoggingInterceptor().let {
                it.level = HttpLoggingInterceptor.Level.BODY
                this.addInterceptor(it)
            }
        }.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}
