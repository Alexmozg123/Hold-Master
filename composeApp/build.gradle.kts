plugins {
    alias(libs.plugins.holdmaster.kmplib)
    alias(libs.plugins.holdmaster.compose)
    alias(libs.plugins.holdmaster.decompose)
    alias(libs.plugins.holdmaster.koin)
}

android {
    namespace = "ru.bortsov.holdmaster.composeapp"
}