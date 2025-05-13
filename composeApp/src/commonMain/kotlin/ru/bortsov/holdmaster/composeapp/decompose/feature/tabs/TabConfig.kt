package ru.bortsov.holdmaster.composeapp.decompose.feature.tabs

import kotlinx.serialization.Serializable

@Serializable
internal sealed class Config {
    @Serializable
    data object Game : Config()
    @Serializable
    data object Profile : Config()
}