package ru.bortsov.holdmaster.feature.photo.data

import ru.bortsov.holdmaster.core.base.platform.PlatformConfig
import ru.bortsov.holdmaster.feature.photo.api.PhotoRepository

internal actual class PhotoRepositoryImpl actual constructor(
    private val platformConfig: PlatformConfig,
) : PhotoRepository {

    override fun takeAPhoto() {
        TODO("Not yet implemented")
    }
}