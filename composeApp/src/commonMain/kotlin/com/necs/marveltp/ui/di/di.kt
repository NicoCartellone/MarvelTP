package com.necs.marveltp.ui.di

import com.necs.marveltp.core.PRIVATE_KEY
import com.necs.marveltp.core.PUBLIC_KEY
import com.necs.marveltp.currentTimeMillis
import com.necs.marveltp.data.local.CharactersDBRepository
import com.necs.marveltp.data.local.CharactersDBRepositoryImpl
import com.necs.marveltp.data.network.CharactersService
import com.necs.marveltp.data.repository.CharactersRepository
import com.necs.marveltp.data.repository.CharactersRepositoryImpl
import com.necs.marveltp.md5
import com.necs.marveltp.ui.screens.characterDetail.CharacterDetailViewModel
import com.necs.marveltp.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val dataModule = module {
    single<CharactersRepository> { CharactersRepositoryImpl(get()) }

    factory { CharactersService(get()) }

    single<CharactersDBRepository> { CharactersDBRepositoryImpl(get()) }

    single {
        val timestamp = currentTimeMillis()
        val md5 = md5(timestamp.toString() + PRIVATE_KEY + PUBLIC_KEY)
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                }, contentType = ContentType.Any)
            }
            install(DefaultRequest){
                headers {
                    append("Accept", "application/json")
                }
                url {
                    protocol = URLProtocol.HTTPS
                    host = "gateway.marvel.com/"
                    parameters.append("ts", timestamp.toString())
                    parameters.append("hash", md5)
                    parameters.append("apikey", PUBLIC_KEY)
                }
            }
        }
    }
}

val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::CharacterDetailViewModel)
}

expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(dataModule, viewModelsModule, nativeModule)
    }
}