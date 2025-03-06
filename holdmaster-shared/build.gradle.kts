plugins {
    alias(libs.plugins.holdmaster.kmplib.ios)
    alias(libs.plugins.holdmaster.compose.ios)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.composeApp)
            // Необходимо явно добавить все зависимости для работы
            // приложения на iOS, чтобы они попали в XCFramework
            implementation(compose.runtime)
            implementation(compose.ui)
        }
    }
}