plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.koin)
}

kotlin {
    explicitApi()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.utils)
            implementation(projects.feature.auth.api)

            api(libs.settings.core)
            api(libs.settings.coroutines)
            implementation(libs.settings.noArg)

            api(libs.ktor.client.core)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.loggin)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.contentNegotiation)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.engine.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.engine.darwin)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.core.base"
}