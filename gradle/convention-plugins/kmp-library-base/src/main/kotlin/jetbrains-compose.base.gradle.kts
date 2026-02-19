import utils.applyIfNeeded
import utils.kmpConfig
import utils.libs

plugins.applyIfNeeded(libs.plugins.jetbrains.compose.compiler.get().pluginId)
plugins.applyIfNeeded(libs.plugins.jetbrains.compose.multiplatform.get().pluginId)

kmpConfig {
    sourceSets {
        commonMain.dependencies {
            api(project(":core:uikit"))
            api(libs.compose.material.icons.core)

            implementation(libs.compose.components.resources)
            implementation(libs.compose.ui.tooling.preview)
        }
    }
}
