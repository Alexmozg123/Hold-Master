plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.compose)
    alias(libs.plugins.holdmaster.decompose)
    alias(libs.plugins.holdmaster.koin)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.game.api)
            implementation(projects.core.utils)
            implementation(libs.coil.compose)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.feature.game.presentation"
}
