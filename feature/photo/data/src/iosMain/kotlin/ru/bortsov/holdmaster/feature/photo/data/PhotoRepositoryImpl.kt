package ru.bortsov.holdmaster.feature.photo.data

import kotlinx.coroutines.flow.Flow
import ru.bortsov.holdmaster.core.base.platform.PlatformConfig
import ru.bortsov.holdmaster.feature.photo.api.PhotoRepository

internal actual class PhotoRepositoryImpl actual constructor(
    platformConfig: PlatformConfig,
) : PhotoRepository {

    override fun getPhoto(): Flow<ByteArray> {
        TODO("Not yet implemented")
    }

    override fun takePhoto() {
        TODO("Not yet implemented")
    }
}