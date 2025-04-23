plugins {
    alias(libs.plugins.holdmaster.kmplib)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.ktx)
        }
    }
}

android {
    namespace = "ru.bortsov.holdmaster.feature.photo.api"
}
