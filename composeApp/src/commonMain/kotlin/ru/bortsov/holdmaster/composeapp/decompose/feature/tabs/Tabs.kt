package ru.bortsov.holdmaster.composeapp.decompose.feature.tabs

import com.arkivanov.decompose.ComponentContext

interface Tabs {
    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Tabs
    }
}