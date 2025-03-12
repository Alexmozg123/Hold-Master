import utils.applyIfNeeded
import utils.kmpConfig
import utils.libs

plugins.applyIfNeeded("koin.base")

kmpConfig {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }
}