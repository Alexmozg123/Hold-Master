package ru.bortsov.holdmaster.feature.photo.data

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bortsov.holdmaster.feature.photo.api.PhotoRepository

val photoDataModule: Module = module {
    singleOf(::PhotoRepositoryImpl) bind PhotoRepository::class
}