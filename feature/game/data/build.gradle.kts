plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.koin)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.game.api)
            implementation(projects.core.base)
            implementation(projects.core.utils)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.feature.game.data"
}
