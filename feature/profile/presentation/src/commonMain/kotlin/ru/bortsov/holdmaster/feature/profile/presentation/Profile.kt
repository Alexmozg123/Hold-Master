package ru.bortsov.holdmaster.feature.profile.presentation

import com.arkivanov.decompose.ComponentContext

interface Profile {

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Profile
    }
}