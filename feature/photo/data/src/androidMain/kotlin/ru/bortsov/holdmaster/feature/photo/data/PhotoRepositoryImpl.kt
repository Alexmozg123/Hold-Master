package ru.bortsov.holdmaster.feature.photo.data

import android.content.ActivityNotFoundException
import android.content.Intent
import android.provider.MediaStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import ru.bortsov.holdmaster.core.base.di.Inject
import ru.bortsov.holdmaster.core.base.platform.PlatformConfig
import ru.bortsov.holdmaster.feature.photo.api.CameraManager
import ru.bortsov.holdmaster.feature.photo.api.PhotoRepository

internal actual class PhotoRepositoryImpl actual constructor(
    private val platformConfig: PlatformConfig,
) : PhotoRepository {

    private val cameraManager: CameraManager by Inject.instance()

    override fun getPhoto(): Flow<ByteArray> = cameraManager.photoFlow()
        .onEach { photo -> println("image PhotoRepositoryImpl $photo") }

    override fun takePhoto() { openCameraApp() }

    private fun openCameraApp() {
        platformConfig.photoLauncher?.let { launcher ->
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                launcher.launch(takePictureIntent)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
            }
        }
    }
}