package com.joaostanzione.iddog.ui.dogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joaostanzione.iddog.domain.dogs.DogsUseCase
import com.joaostanzione.iddog.ui.BaseViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.launch

internal class DogsViewModel(
    coroutineContext: CoroutineContext,
    private val dogsUseCase: DogsUseCase
) : BaseViewModel(coroutineContext) {

    private val _states = MutableLiveData<DogsState>()
    val states: LiveData<DogsState>
        get() = _states

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    fun getDogsByCategory(dogCategory: String, token: String?) {
        uiScope.launch {
            _showLoading.value = true
            try {
                _states.value = DogsState.Success(dogsUseCase.getDogs(dogCategory, token))
            } catch (error: Exception) {
                _states.value = DogsState.Error(error)
            } finally {
                _showLoading.value = false
            }
        }
    }

    sealed class DogsState {
        data class Success(val dogs: List<String>?) : DogsState()
        data class Error(val exception: Exception) : DogsState()
    }
}
