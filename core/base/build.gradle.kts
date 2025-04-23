plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.koin)
}

kotlin {
    explicitApi()
}

android {
    namespace = "ru.bortsov.holdmaster.core.base"
}