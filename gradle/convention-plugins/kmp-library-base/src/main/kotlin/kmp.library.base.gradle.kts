import utils.applyIfNeeded
import utils.kmpConfig
import utils.libs

plugins.applyIfNeeded(libs.plugins.jetbrains.kotlin.multiplatform.get().pluginId)
plugins.applyIfNeeded(libs.plugins.jetbrains.kotlin.serialization.get().pluginId)

kmpConfig {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.androidx.annotation)
            implementation(libs.kotlinx.datetime)
            api(libs.loggin.napier)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}
