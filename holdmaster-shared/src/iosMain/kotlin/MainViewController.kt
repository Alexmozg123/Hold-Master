import androidx.compose.ui.window.ComposeUIViewController
import ru.bortsov.holdmaster.composeapp.HoldMasterApp
import ru.bortsov.holdmaster.composeapp.KoinStarter

fun MainViewController() = ComposeUIViewController(
    configure = { KoinStarter().init() }
) {
    HoldMasterApp()
}
