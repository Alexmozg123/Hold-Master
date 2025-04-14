package ru.bortsov.holdmaster

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.arkivanov.decompose.defaultComponentContext
import kotlinx.coroutines.launch
import ru.bortsov.holdmaster.composeapp.HoldMasterApp
import ru.bortsov.holdmaster.composeapp.decompose.Root
import ru.bortsov.holdmaster.core.base.di.Inject
import ru.bortsov.holdmaster.core.base.platform.PlatformConfig
import ru.bortsov.holdmaster.feature.photo.api.PhotoPusher

class MainActivity : ComponentActivity() {

    private val photoPusher: PhotoPusher by Inject.instance()
    private val platformConfig: PlatformConfig by Inject.instance()

    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val imageBitmap = result.data?.extras?.get("data") as? Bitmap
                println("imageBitmap$imageBitmap")
                imageBitmap?.let {
                    lifecycleScope.launch { photoPusher.pushPhoto(it) }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        platformConfig.photoLauncher = takePictureLauncher
        val rootComponentFactory: Root.Factory by Inject.instance()
        val rootComponent = rootComponentFactory(defaultComponentContext())

        setContent {
            HoldMasterApp(component = rootComponent, modifier = Modifier.fillMaxSize())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        platformConfig.photoLauncher = null
    }
}
