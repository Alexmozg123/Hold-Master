plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.holdmaster.android.base)
    alias(libs.plugins.holdmaster.compose.jetpack.base)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.manifestGuard)
}

android {
    namespace = "ru.bortsov.holdmaster"

    defaultConfig {
        applicationId = "ru.bortsov.holdmaster"
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    lint {
        checkReleaseBuilds = false
        checkDependencies = true
    }

    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(projects.composeApp)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
}

androidComponents.beforeVariants { variant ->
    manifestGuard {
        guardVariant(variant.name) {
            enabled = true
            compareOnAssemble = false

            val baseDir = layout.projectDirectory.dir("manifest/${variant.name}")
            referenceFile = baseDir.file("manifest-original.xml")
            htmlDiffFile = baseDir.file("manifest-diff.html")

            ignore {
                ignoreAppVersionChanges = true
            }
        }
    }
}


