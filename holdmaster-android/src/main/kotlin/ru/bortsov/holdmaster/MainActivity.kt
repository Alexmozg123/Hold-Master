package ru.bortsov.holdmaster

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import ru.bortsov.holdmaster.composeapp.HoldMasterApp
import ru.bortsov.holdmaster.composeapp.Inject
import ru.bortsov.holdmaster.composeapp.decompose.Root

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponentFactory: Root.Factory by Inject.instance()
        val rootComponent = rootComponentFactory(defaultComponentContext())

        setContent {
            HoldMasterApp(component = rootComponent, modifier = Modifier.fillMaxSize())
        }
    }
}
