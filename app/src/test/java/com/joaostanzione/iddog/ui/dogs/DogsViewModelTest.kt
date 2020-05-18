package com.joaostanzione.iddog.ui.dogs

import androidx.lifecycle.Observer
import com.joaostanzione.iddog.data.dogs.DogsResponse
import com.joaostanzione.iddog.domain.dogs.DogsUseCase
import com.joaostanzione.iddog.BaseUnitTest
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertTrue
import org.junit.Test

internal class DogsViewModelTest : BaseUnitTest() {

    @MockK
    lateinit var dogsUseCase: DogsUseCase

    private lateinit var viewModel: DogsViewModel

    @Test
    fun getDogsByCategory_OnSuccess_ShouldCallSuccessCallback() {
        val dogsData: DogsResponse = getMockJson(DOGS_JSON)
        coEvery { dogsUseCase.getDogs(any(), any()) } returns dogsData.dogsPhotos
        initViewModel()

        val stateObserver = mockk<Observer<DogsViewModel.DogsState>>(relaxed = true)

        viewModel.getDogsByCategory("labrador", "token")

        viewModel.states.observeForever(stateObserver)

        verify { stateObserver.onChanged(any()) }

        assertTrue(viewModel.states.value is DogsViewModel.DogsState.Success)
    }

    @Test
    fun getDogsByCategory_OnError_ShouldCallErrorCallback() {
        coEvery { dogsUseCase.getDogs(any(), any()) } throws Exception()
        initViewModel()

        val stateObserver = mockk<Observer<DogsViewModel.DogsState>>(relaxed = true)

        viewModel.getDogsByCategory("labrador", "token")

        viewModel.states.observeForever(stateObserver)

        verify { stateObserver.onChanged(any()) }

        assertTrue(viewModel.states.value is DogsViewModel.DogsState.Error)
    }

    private fun initViewModel() {
        viewModel = DogsViewModel(
            Dispatchers.Unconfined,
            dogsUseCase
        )
    }

    companion object {
        const val DOGS_JSON = "Dogs.json"
    }
}
