package ru.bortsov.holdmaster.feature.auth.api.model

data class SingUpForm(
    val name: String,
    val middleName: String,
    val lastName: String,
    val password: String,
    val email: String,
    val phone: String,
    val parking: Parking,
    val inn: String,
)
