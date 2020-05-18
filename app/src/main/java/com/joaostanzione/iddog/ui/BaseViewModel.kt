package com.joaostanzione.iddog.ui

import androidx.lifecycle.ViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

open class BaseViewModel(coroutineContext: CoroutineContext) : ViewModel() {

    private val viewModelJob = Job()

    protected open val uiScope = CoroutineScope(coroutineContext + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
