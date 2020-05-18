package com.joaostanzione.iddog.infrastructure.di

import com.joaostanzione.iddog.domain.login.LoginUseCase
import org.koin.dsl.module

internal val domainModule = module {
    single { LoginUseCase(get()) }
}
