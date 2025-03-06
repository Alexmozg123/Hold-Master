import utils.ProjectTargets
import utils.applyIfNeeded
import utils.javaVersion
import utils.jvmTarget
import utils.kotlinJvmCompilerOptions
import utils.libs
import org.gradle.kotlin.dsl.dependencies

plugins.apply("android.base")
if (plugins.hasPlugin(libs.plugins.jetbrains.kotlin.multiplatform.get().pluginId)) {
    plugins.applyIfNeeded(libs.plugins.jetbrains.kotlin.android.get().pluginId)
}

project.dependencies {
    "implementation"(libs.kotlinx.coroutines.android)
    "implementation"(libs.androidx.core)
    "implementation"(libs.androidx.annotation)
}

kotlinJvmCompilerOptions {
    jvmTarget.set(libs.jvmTarget(ProjectTargets.Android))
    freeCompilerArgs.add("-Xjdk-release=${libs.javaVersion(ProjectTargets.Android)}")
}
