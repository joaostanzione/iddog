package com.joaostanzione.iddog.infrastructure.di

import com.joaostanzione.iddog.ui.login.LoginViewModel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    single<CoroutineContext> { Dispatchers.Main }
    viewModel { LoginViewModel(get(), get()) }
}
