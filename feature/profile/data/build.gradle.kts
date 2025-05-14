plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.koin)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.profile.api)
            implementation(projects.core.base)
            implementation(projects.core.utils)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.feature.profile.data"
}
