import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import ru.bortsov.holdmaster.composeapp.HoldMasterApp
import ru.bortsov.holdmaster.composeapp.root.Root

fun MainViewController(rootComponent: Root) = ComposeUIViewController {
    HoldMasterApp(
        component = rootComponent,
        modifier = Modifier.fillMaxSize()
    )
}
