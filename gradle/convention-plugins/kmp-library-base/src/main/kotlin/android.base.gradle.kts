import utils.ProjectTargets
import utils.androidConfig
import utils.javaVersion
import utils.libs
import utils.config.requestedAndroidAbis

androidConfig {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        ndk {
            requestedAndroidAbis.takeUnless { it.isNullOrEmpty() }?.let { abis: List<String> ->
                abiFilters.addAll(abis)
            }
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        val javaVersion = libs.javaVersion(ProjectTargets.Android)
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
}

dependencies {
    "testImplementation"(libs.kotlin.test)
    "testImplementation"(libs.kotlin.test.junit)
    "testImplementation"(libs.junit)
}
