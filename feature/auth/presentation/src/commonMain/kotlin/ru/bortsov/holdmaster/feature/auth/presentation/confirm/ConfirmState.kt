package ru.bortsov.holdmaster.feature.auth.presentation.confirm

import kotlinx.serialization.Serializable

@Serializable
data class ConfirmState(
    val confirmCode: String = "",
    val isLoading: Boolean = false,
)