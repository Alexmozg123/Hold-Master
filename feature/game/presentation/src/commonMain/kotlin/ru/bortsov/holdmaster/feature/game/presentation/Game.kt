package ru.bortsov.holdmaster.feature.game.presentation

import com.arkivanov.decompose.ComponentContext

interface Game {

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Game
    }
}