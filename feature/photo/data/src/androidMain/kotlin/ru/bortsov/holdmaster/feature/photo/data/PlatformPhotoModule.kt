package ru.bortsov.holdmaster.feature.photo.data

import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.bortsov.holdmaster.feature.photo.api.PhotoPusher
import ru.bortsov.holdmaster.feature.photo.api.PhotoRepository

internal actual val platformPhotoModule: Module = module {
    singleOf(::PhotoRepositoryImpl) {
        bind<PhotoRepository>()
        bind<PhotoPusher>()
    }
}