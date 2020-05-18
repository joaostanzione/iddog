package com.joaostanzione.iddog.infrastructure.di

import com.joaostanzione.iddog.Api
import com.joaostanzione.iddog.data.dogs.DogsRemoteRepository
import com.joaostanzione.iddog.data.dogs.DogsRepository
import com.joaostanzione.iddog.data.login.LoginRemoteRepository
import com.joaostanzione.iddog.data.login.LoginRepository
import com.joaostanzione.iddog.domain.dogs.DogsUseCase
import com.joaostanzione.iddog.domain.login.LoginUseCase
import com.joaostanzione.iddog.ui.dogs.DogsViewModel
import com.joaostanzione.iddog.ui.login.LoginViewModel
import io.mockk.mockk
import java.util.UUID
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testModule = module {
    single { mockk<Api>(relaxed = true) }
    factory { UUID.randomUUID() }
    single<CoroutineContext> { Dispatchers.Main }
    single { LoginUseCase(get()) }
    single { DogsUseCase(get()) }
    single<LoginRepository> { LoginRemoteRepository(get()) }
    single<DogsRepository> { DogsRemoteRepository(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { DogsViewModel(get(), get()) }
}
