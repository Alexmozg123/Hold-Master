package ru.bortsov.holdmaster.feature.photo.data

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bortsov.holdmaster.feature.photo.api.CameraManager
import ru.bortsov.holdmaster.feature.photo.api.PhotoPusher

internal actual val platformPhotoModule: Module = module {
    singleOf(::CameraManagerImpl) bind CameraManager::class
    singleOf(::CameraManagerImpl) bind PhotoPusher::class
}