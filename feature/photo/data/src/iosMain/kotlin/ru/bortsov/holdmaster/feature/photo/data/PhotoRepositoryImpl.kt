package ru.bortsov.holdmaster.feature.photo.data

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import platform.Foundation.NSData
import platform.UIKit.UIApplication
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerCameraCaptureMode
import platform.UIKit.UIImagePickerControllerDelegateProtocol
import platform.UIKit.UIImagePickerControllerEditedImage
import platform.UIKit.UIImagePickerControllerOriginalImage
import platform.UIKit.UIImagePickerControllerSourceType
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.darwin.NSObject
import platform.darwin.TARGET_OS_SIMULATOR
import platform.posix.memcpy
import ru.bortsov.holdmaster.core.base.platform.PlatformConfig
import ru.bortsov.holdmaster.feature.photo.api.PhotoRepository

internal actual class PhotoRepositoryImpl actual constructor(
    platformConfig: PlatformConfig,
) : PhotoRepository {

    private val _photoFlow = MutableStateFlow<ByteArray?>(null)
    private val scope = CoroutineScope(Dispatchers.Main)

    private val imagePicker = UIImagePickerController()
    private val cameraDelegate = object : NSObject(),
        UIImagePickerControllerDelegateProtocol,
        UINavigationControllerDelegateProtocol {
        override fun imagePickerController(
            picker: UIImagePickerController, didFinishPickingMediaWithInfo: Map<Any?, *>
        ) {
            val image = didFinishPickingMediaWithInfo
                .getValue(UIImagePickerControllerEditedImage) as? UIImage
                ?: didFinishPickingMediaWithInfo
                    .getValue(UIImagePickerControllerOriginalImage) as? UIImage

            image?.let { i ->
                val imageData = UIImageJPEGRepresentation(i, 0.8)
                imageData?.let { iData ->
                    scope.launch { _photoFlow.emit(iData.toByteArray()) }
                }
            }

            picker.dismissViewControllerAnimated(true, null)
        }
    }

    init {
        initialSettings()
    }

    override fun getPhoto(): Flow<ByteArray> = _photoFlow.asStateFlow().filterNotNull()

    override fun takePhoto() {
        UIApplication.sharedApplication
            .keyWindow
            ?.rootViewController
            ?.presentViewController(imagePicker, true, null)
    }

    private fun initialSettings() {
        imagePicker.setAllowsEditing(true)
        imagePicker.setDelegate(cameraDelegate)

        val isSimulator = TARGET_OS_SIMULATOR != 0
        chooseSourceTypeForImagePicker(isSimulator)
    }

    /**
     * Если стартовать на IOS симуляторе, то важно назначит галерею вместо камеры,
     * так как на симуряторе нет симуляции камеры.
     */
    private fun chooseSourceTypeForImagePicker(isSimulator: Boolean) {
        if (isSimulator) {
            imagePicker.setSourceType(UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeSavedPhotosAlbum)
        } else {
            imagePicker.setSourceType(UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera)
            imagePicker.setCameraCaptureMode(UIImagePickerControllerCameraCaptureMode.UIImagePickerControllerCameraCaptureModePhoto)
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun NSData.toByteArray(): ByteArray {
        return ByteArray(length.toInt()).apply {
            usePinned { memcpy(it.addressOf(0), bytes, length) }
        }
    }
}