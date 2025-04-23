plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.decompose)
}

kotlin {
    explicitApi()
}

android {
    namespace = "ru.bortsov.holdmaster.core.utils"
}