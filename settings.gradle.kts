rootProject.name = "HoldMaster"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("gradle/convention-plugins")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(
    ":core:base",
    ":core:utils",
    ":core:uikit",

    ":feature:photo:api",
    ":feature:photo:data",
    ":feature:photo:presentation",

    ":feature:auth:api",
    ":feature:auth:data",
    ":feature:auth:presentation",

    ":composeApp",
    ":holdmaster-android",
    ":holdmaster-shared",
)

project(":holdmaster-shared").name = "HoldMasterIOShared"