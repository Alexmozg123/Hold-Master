package ru.bortsov.holdmaster.feature.photo.data

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import ru.bortsov.holdmaster.core.base.platform.PlatformConfig
import ru.bortsov.holdmaster.feature.photo.api.PhotoPusher
import ru.bortsov.holdmaster.feature.photo.api.PhotoRepository
import java.io.ByteArrayOutputStream

internal actual class PhotoRepositoryImpl actual constructor(
    private val platformConfig: PlatformConfig,
) : PhotoRepository, PhotoPusher {

    private val _photoFlow = MutableStateFlow<ByteArray?>(null)

    override fun getPhoto(): Flow<ByteArray> = _photoFlow.asStateFlow().filterNotNull()

    override fun takePhoto() { openCameraApp() }

    override suspend fun pushPhoto(photo: Bitmap) {
        val byteArray = photo.bitmapToByteArray()
        _photoFlow.emit(byteArray)
    }

    private fun openCameraApp() {
        platformConfig.photoLauncher?.let { launcher ->
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                launcher.launch(takePictureIntent)
            } catch (e: ActivityNotFoundException) {
                Napier.e { e.message.toString() }
            }
        }
    }

    private fun Bitmap.bitmapToByteArray(): ByteArray {
        val stream = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }
}