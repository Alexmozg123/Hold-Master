package ru.bortsov.holdmaster.composeapp.decompose

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootConfig {

    @Serializable
    sealed interface Slot : RootConfig {
        @Serializable
        data class ErrorDialog(val error: String) : Slot
    }

    @Serializable
    sealed interface Stack : RootConfig {
        @Serializable
        data object Splash : Stack
        @Serializable
        data object Onboarding : Stack
        @Serializable
        data object Tabs : Stack
        @Serializable
        data object Auth : Stack
    }
}