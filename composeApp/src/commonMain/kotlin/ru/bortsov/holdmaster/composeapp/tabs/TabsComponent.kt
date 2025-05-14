package ru.bortsov.holdmaster.composeapp.tabs

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import ru.bortsov.holdmaster.composeapp.tabs.Tabs.Child
import ru.bortsov.holdmaster.feature.game.presentation.Game
import ru.bortsov.holdmaster.feature.profile.presentation.Profile

class TabsComponent(
    componentContext: ComponentContext,
    private val gameComponentFactory: Game.Factory,
    private val profileComponentFactory: Profile.Factory,
): Tabs, ComponentContext by componentContext {

    private val nav = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = nav,
            serializer = Config.serializer(),
            initialConfiguration = Config.Game,
            childFactory = ::child,
        )

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            Config.Game -> Child.GameChild(gameComponentFactory(componentContext))
            Config.Profile -> Child.ProfileChild(profileComponentFactory(componentContext))
        }

    override fun onGameClicked() = nav.bringToFront(Config.Game)

    override fun onProfileTabClicked() = nav.bringToFront(Config.Profile)

    class Factory(
        private val gameComponentFactory: Game.Factory,
        private val profileComponentFactory: Profile.Factory,
    ) : Tabs.Factory {
        override fun invoke(componentContext: ComponentContext): TabsComponent {
            return TabsComponent(
                componentContext = componentContext,
                gameComponentFactory = gameComponentFactory,
                profileComponentFactory = profileComponentFactory,
            )
        }
    }
}