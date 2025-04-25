package ru.bortsov.holdmaster.feature.auth.data.ktor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.bortsov.holdmaster.feature.auth.api.model.SingUpForm

@Serializable
data class SingUpFormRequest(
    @SerialName("name") val name: String,
    @SerialName("middleName") val middleName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("password") val password: String,
    @SerialName("email") val email: String,
    @SerialName("phone") val phone: String,
    @SerialName("parking") val parking: String,
    @SerialName("inn") val inn: String,
)

fun SingUpForm.toSingUpFormRequest() = SingUpFormRequest(
    name = name,
    middleName = middleName,
    lastName = lastName,
    password = password,
    email = email,
    phone = phone,
    parking = parking.name,
    inn = inn,
)
