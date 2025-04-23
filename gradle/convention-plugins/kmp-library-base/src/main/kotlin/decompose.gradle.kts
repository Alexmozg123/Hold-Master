import org.gradle.kotlin.dsl.invoke
import utils.applyIfNeeded
import utils.kmpConfig
import utils.libs

plugins.applyIfNeeded(libs.plugins.jetbrains.kotlin.serialization.get().pluginId)

kmpConfig {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.decompose.core)
            implementation(libs.decompose.extensionsComposeJetbrains)
            implementation(libs.essenty.lifecycle)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}