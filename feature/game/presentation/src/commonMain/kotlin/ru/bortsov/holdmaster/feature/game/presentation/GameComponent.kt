package ru.bortsov.holdmaster.feature.game.presentation

import com.arkivanov.decompose.ComponentContext

internal class GameComponent(
    componentContext: ComponentContext
): Game, ComponentContext by componentContext {

    class Factory : Game.Factory {
        override fun invoke(componentContext: ComponentContext): Game {
            return GameComponent(componentContext = componentContext)
        }
    }
}