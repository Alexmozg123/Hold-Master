package ru.bortsov.holdmaster.feature.auth.data

import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bortsov.holdmaster.feature.auth.api.AuthRepository
import ru.bortsov.holdmaster.feature.auth.data.ktor.RemoteDataSource
import ru.bortsov.holdmaster.feature.auth.data.settings.LocalDataSource

val authModule: Module = module {
    factoryOf(::RemoteDataSource)
    factoryOf(::LocalDataSource)
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}