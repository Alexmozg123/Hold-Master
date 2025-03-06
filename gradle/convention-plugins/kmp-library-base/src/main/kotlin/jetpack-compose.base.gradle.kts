import utils.androidConfig
import utils.applyIfNeeded
import utils.libs

plugins.applyIfNeeded(libs.plugins.jetbrains.compose.compiler.get().pluginId)

androidConfig {
    buildFeatures {
        compose = true
    }
}
