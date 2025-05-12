package ru.bortsov.holdmaster

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
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
            SetSystemBarColors()
            HoldMasterApp(component = rootComponent, modifier = Modifier.fillMaxSize())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        platformConfig.photoLauncher = null
    }
}

@Composable
private fun ComponentActivity.SetSystemBarColors() {

    val lightScrim = rememberSaveable { Color.argb(0, 0x1b, 0x1b, 0x1b) }
    val darkScrim = rememberSaveable { Color.argb(0, 0xFF, 0xFF, 0xFF) }
    val isSystemInDarkTheme = isSystemInDarkTheme()

    LaunchedEffect(isSystemInDarkTheme) {
        if (isSystemInDarkTheme) {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.light(
                    scrim = Color.TRANSPARENT,
                    darkScrim = darkScrim
                ),
                navigationBarStyle = SystemBarStyle.light(
                    scrim = Color.TRANSPARENT,
                    darkScrim = Color.TRANSPARENT
                )
            )
        } else {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.dark(lightScrim),
                navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
            )
        }
    }
}
