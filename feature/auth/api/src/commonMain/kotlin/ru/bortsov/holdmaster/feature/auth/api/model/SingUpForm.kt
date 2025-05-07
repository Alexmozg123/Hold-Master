package ru.bortsov.holdmaster.feature.auth.api.model

import kotlinx.serialization.Serializable

@Serializable
data class SingUpForm(
    val name: String,
    val middleName: String,
    val lastName: String,
    val password: String,
    val email: String,
)
