plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.jetbrains.compose.multiplatform)
    alias(libs.plugins.jetbrains.compose.compiler)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "ru.bortsov.holdmaster.core.uikit.resources"
    generateResClass = auto
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.compose.runtime)
            api(libs.compose.foundation)
            api(libs.compose.ui)
            api(libs.compose.ui.tooling.preview )
            api(libs.compose.material3)
            api(libs.compose.material3.adaptive.navigation.suite)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.core.uikit"

    buildFeatures {
        compose = true
    }
}