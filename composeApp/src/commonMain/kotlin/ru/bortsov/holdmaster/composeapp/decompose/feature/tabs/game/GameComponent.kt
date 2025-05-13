package ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.game

import com.arkivanov.decompose.ComponentContext

class GameComponent(
    componentContext: ComponentContext
): Game, ComponentContext by componentContext {

    class Factory : Game.Factory {
        override fun invoke(componentContext: ComponentContext): Game {
            return GameComponent(componentContext = componentContext)
        }
    }
}