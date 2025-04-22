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
import platform.posix.memcpy
import ru.bortsov.holdmaster.core.base.platform.PlatformConfig
import ru.bortsov.holdmaster.feature.photo.api.PhotoRepository

internal actual class PhotoRepositoryImpl actual constructor(
    platformConfig: PlatformConfig,
) : PhotoRepository {

    private val _photoFlow = MutableStateFlow<ByteArray?>(null)
    private val scope = CoroutineScope(Dispatchers.Main)

    // TODO: в этом месте падает
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
        imagePicker.setSourceType(UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera)
        imagePicker.setAllowsEditing(true)
        imagePicker.setCameraCaptureMode(UIImagePickerControllerCameraCaptureMode.UIImagePickerControllerCameraCaptureModePhoto)
        imagePicker.setDelegate(cameraDelegate)
    }

    override fun getPhoto(): Flow<ByteArray> = _photoFlow.asStateFlow().filterNotNull()

    override fun takePhoto() {
        UIApplication.sharedApplication
            .keyWindow
            ?.rootViewController
            ?.presentViewController(imagePicker, true, null)
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun NSData.toByteArray(): ByteArray {
        return ByteArray(length.toInt()).apply {
            usePinned { memcpy(it.addressOf(0), bytes, length) }
        }
    }
}