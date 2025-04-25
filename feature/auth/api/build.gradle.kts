plugins {
    alias(libs.plugins.holdmaster.kmplib)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.utils)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.feature.auth.api"
}
