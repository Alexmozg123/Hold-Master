package ru.bortsov.holdmaster.feature.auth.presentation.confirm

import kotlinx.serialization.Serializable

@Serializable
data class ConfirmState(
    val confirmCode: String = "",
    val timer: Int = TIMER_DURATION,
    val isTimeUp: Boolean = false,
    val isLoading: Boolean = false,
) {
    companion object {
        const val TIMER_DURATION = 60
    }
}