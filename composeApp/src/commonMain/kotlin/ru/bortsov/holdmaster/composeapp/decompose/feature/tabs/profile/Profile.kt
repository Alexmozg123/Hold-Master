package ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.profile

import com.arkivanov.decompose.ComponentContext

interface Profile {

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Profile
    }
}