package ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.game

import com.arkivanov.decompose.ComponentContext

interface Game {

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Game
    }
}