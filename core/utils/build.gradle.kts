plugins {
    alias(libs.plugins.holdmaster.kmplib)
}

kotlin {
    explicitApi()
}

android {
    namespace = "ru.bortsov.holdmaster.core.utils"
}