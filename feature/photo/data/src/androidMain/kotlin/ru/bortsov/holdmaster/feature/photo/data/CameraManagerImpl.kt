package ru.bortsov.holdmaster.feature.photo.data

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.bortsov.holdmaster.feature.photo.api.CameraManager
import ru.bortsov.holdmaster.feature.photo.api.PhotoPusher
import java.io.ByteArrayOutputStream

internal class CameraManagerImpl : CameraManager, PhotoPusher {

    private val _photoFlow = MutableSharedFlow<ByteArray>(replay = 1)

    override fun photoFlow(): Flow<ByteArray> = _photoFlow.asSharedFlow()

    override suspend fun pushPhoto(photo: Bitmap) {
        _photoFlow.emit(photo.bitmapToByteArray())
    }

    private fun Bitmap.bitmapToByteArray(): ByteArray {
        val stream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }
}