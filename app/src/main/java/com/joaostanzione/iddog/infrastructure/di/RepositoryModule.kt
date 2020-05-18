package com.joaostanzione.iddog.infrastructure.di

import com.joaostanzione.iddog.data.login.LoginRemoteRepository
import com.joaostanzione.iddog.data.login.LoginRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    single<LoginRepository> { LoginRemoteRepository(get()) }
}
