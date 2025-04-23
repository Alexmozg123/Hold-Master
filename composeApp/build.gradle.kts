plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.compose)
    alias(libs.plugins.holdmaster.decompose)
    alias(libs.plugins.holdmaster.koin)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.base)

            implementation(projects.feature.photo.api)
            implementation(projects.feature.photo.data)
            implementation(projects.feature.photo.presentation)
        }

        androidMain.dependencies {
            api(projects.feature.photo.api)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.composeapp"
}