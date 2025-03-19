plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.compose)
    alias(libs.plugins.holdmaster.decompose)
    alias(libs.plugins.holdmaster.koin)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.photo.api)
            implementation(projects.core.utils)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.feature.photo.presentation"
}
