package ru.bortsov.holdmaster.composeapp.tabs

import kotlinx.serialization.Serializable

@Serializable
internal sealed class Config {
    @Serializable
    data object Game : Config()
    @Serializable
    data object Profile : Config()
}