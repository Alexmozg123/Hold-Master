import utils.ProjectTargets
import utils.androidConfig
import utils.applyIfNeeded
import utils.jvmTarget
import utils.kmpConfig
import utils.libs

plugins.applyIfNeeded(libs.plugins.jetbrains.kotlin.multiplatform.get().pluginId)
plugins.applyIfNeeded(libs.plugins.android.library.get().pluginId)
plugins.applyIfNeeded("kmp.library.base")

kmpConfig {
    androidTarget {
        compilerOptions {
            jvmTarget.set(libs.jvmTarget(ProjectTargets.Android))
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.core)
            implementation(libs.kotlinx.coroutines.android)
        }

        val androidUnitTest by getting
        androidUnitTest.dependencies {
            implementation(libs.kotlin.test.junit)
            implementation(libs.junit)
        }
    }
}

androidConfig {
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/res")
    }
}

plugins.apply("android.base")
