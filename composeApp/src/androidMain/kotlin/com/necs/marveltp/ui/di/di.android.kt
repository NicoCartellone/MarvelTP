package com.necs.marveltp.ui.di

import com.necs.marveltp.createDriver
import com.necs.marveltp.data.local.CharactersDBRepository
import com.necs.marveltp.data.local.CharactersDBRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModule: Module = module {
    single { createDriver(get()) }
    single<CharactersDBRepository> { CharactersDBRepositoryImpl(get()) }
}