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
            implementation(projects.core.utils)

            implementation(projects.feature.photo.api)
            implementation(projects.feature.photo.data)
            implementation(projects.feature.photo.presentation)

            implementation(projects.feature.game.api)
            implementation(projects.feature.game.data)
            implementation(projects.feature.game.presentation)

            implementation(projects.feature.profile.api)
            implementation(projects.feature.profile.data)
            implementation(projects.feature.profile.presentation)

            implementation(projects.feature.auth.api)
            implementation(projects.feature.auth.data)
            implementation(projects.feature.auth.presentation)
        }

        androidMain.dependencies {
            api(projects.feature.photo.api)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.composeapp"
}