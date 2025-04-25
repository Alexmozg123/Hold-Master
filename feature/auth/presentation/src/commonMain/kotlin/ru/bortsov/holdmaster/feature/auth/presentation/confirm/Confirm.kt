package ru.bortsov.holdmaster.feature.auth.presentation.confirm

import com.arkivanov.decompose.ComponentContext

internal interface Confirm {

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Confirm
    }
}