plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.decompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
        }
    }
    explicitApi()
}

android {
    namespace = "ru.bortsov.holdmaster.core.utils"
}