plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.koin)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.photo.api)
            implementation(projects.core.base)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.feature.photo.data"
}
