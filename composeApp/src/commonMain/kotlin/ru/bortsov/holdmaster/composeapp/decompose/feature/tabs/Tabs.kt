package ru.bortsov.holdmaster.composeapp.decompose.feature.tabs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.game.Game
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.profile.Profile

interface Tabs : BackHandlerOwner {

    val stack: Value<ChildStack<*, Child>>

    fun onGameClicked()
    fun onProfileTabClicked()

    sealed class Child {
        data class GameChild(val component: Game): Child()
        data class ProfileChild(val component: Profile): Child()
    }

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): Tabs
    }
}