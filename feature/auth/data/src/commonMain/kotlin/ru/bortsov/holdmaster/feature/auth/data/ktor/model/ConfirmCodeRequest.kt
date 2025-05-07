package ru.bortsov.holdmaster.feature.auth.data.ktor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfirmCodeRequest(
    @SerialName("code") val code: String,
)
