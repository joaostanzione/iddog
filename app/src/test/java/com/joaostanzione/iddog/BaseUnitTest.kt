package com.joaostanzione.iddog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import org.junit.Before
import org.junit.Rule

internal open class BaseUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() = MockKAnnotations.init(this, relaxed = true)

    protected inline fun <reified T> getMockJson(jsonFile: String): T {
        val file = readFile(jsonFile)

        return Gson().fromJson(file, T::class.java)
    }

    protected fun readFile(file: String): Reader {
        val inputStream = javaClass.classLoader?.getResourceAsStream(file)

        return BufferedReader(InputStreamReader(inputStream))
    }
}
