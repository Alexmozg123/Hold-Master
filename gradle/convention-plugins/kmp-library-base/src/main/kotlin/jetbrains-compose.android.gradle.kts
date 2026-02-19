import utils.androidConfig
import utils.applyIfNeeded
import utils.kmpConfig
import org.gradle.kotlin.dsl.invoke
import utils.libs

plugins.applyIfNeeded("jetbrains-compose.base")
plugins.apply("jetpack-compose.base")

kmpConfig {
    androidConfig {
        buildFeatures {
            compose = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.compose.ui.tooling)
        }
    }
}
