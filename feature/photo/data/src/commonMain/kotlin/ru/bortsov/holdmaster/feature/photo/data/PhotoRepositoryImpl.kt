package ru.bortsov.holdmaster.feature.photo.data

import ru.bortsov.holdmaster.core.base.platform.PlatformConfig
import ru.bortsov.holdmaster.feature.photo.api.PhotoRepository

internal expect class PhotoRepositoryImpl(platformConfig: PlatformConfig) : PhotoRepository