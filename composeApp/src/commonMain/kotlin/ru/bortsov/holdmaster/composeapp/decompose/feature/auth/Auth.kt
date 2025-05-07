package ru.bortsov.holdmaster.composeapp.decompose.feature.auth

import com.arkivanov.decompose.ComponentContext

interface Auth {
    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Auth
    }
}