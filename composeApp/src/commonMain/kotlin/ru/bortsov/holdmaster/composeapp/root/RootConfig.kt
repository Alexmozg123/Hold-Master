package ru.bortsov.holdmaster.composeapp.root

import kotlinx.serialization.Serializable
import ru.bortsov.holdmaster.core.utils.RootError

@Serializable
sealed interface RootConfig {

    @Serializable
    sealed interface Slot : RootConfig {
        @Serializable
        data class ErrorDialog(val error: RootError) : Slot
    }

    @Serializable
    sealed interface Stack : RootConfig {
        @Serializable
        data object Splash : Stack
        @Serializable
        data object Tabs : Stack
        @Serializable
        data object Auth : Stack
        @Serializable
        data object TakePhoto : Stack
    }
}