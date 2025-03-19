plugins {
    alias(libs.plugins.holdmaster.kmplib.ios)
    alias(libs.plugins.holdmaster.compose.ios)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.composeApp)
            api(projects.core.base)
            api(libs.decompose.core)
            api(libs.essenty.lifecycle)
            api(libs.koin.core)

            implementation(compose.runtime)
            implementation(compose.ui)
        }
    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class)
        .configureEach {
            binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class)
                .configureEach {
                    if (baseName == project.name) {
                        export(projects.composeApp)
                        export(projects.core.base)
                        export(libs.decompose.core)
                        export(libs.essenty.lifecycle)
                        export(libs.koin.core)
                    }
                }
        }
}