package com.joaostanzione.iddog

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import io.mockk.coEvery
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
internal abstract class BaseTest : KoinTest {

    private val api: Api by inject()

    private inline fun <reified T> getMockJson(jsonFile: String): T {
        val file = readFile(jsonFile)

        return Gson().fromJson(file, T::class.java)
    }

    protected fun readFile(file: String): Reader {
        val inputStream = javaClass.classLoader?.getResourceAsStream(file)

        return BufferedReader(InputStreamReader(inputStream))
    }

    fun setupLoginMock(loginFile: String = LOGIN_JSON) {
        coEvery { api.doLogin(any()) } returns getMockJson(loginFile)
    }

    fun setupDogsMock(dogsFile: String = DOGS_JSON_SUCCESS) {
        coEvery { api.getDogs(any(), any()) } returns getMockJson(dogsFile)
    }

    fun setupDogsError() {
        coEvery { api.getDogs(any(), any()) } throws Exception()
    }

    companion object {
        const val LOGIN_JSON = "Login.json"
        const val DOGS_JSON_SUCCESS = "Dogs.json"
        const val DOGS_JSON_EMPTY = "DogsEmpty.json"
    }
}
