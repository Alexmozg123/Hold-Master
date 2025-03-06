import utils.androidConfig
import utils.applyIfNeeded
import utils.composeExt
import utils.kmpConfig
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.invoke

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
            implementation(composeExt.dependencies.preview)
        }
    }

    dependencies {
        "debugImplementation"(composeExt.dependencies.uiTooling)
    }
}
