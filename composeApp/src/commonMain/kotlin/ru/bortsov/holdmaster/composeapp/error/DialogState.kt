package ru.bortsov.holdmaster.composeapp.error

import kotlinx.serialization.Serializable
import ru.bortsov.holdmaster.core.utils.RootError

@Serializable
data class DialogState(
    val error: RootError,
)
