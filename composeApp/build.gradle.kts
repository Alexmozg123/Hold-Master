plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.composeapp"
}