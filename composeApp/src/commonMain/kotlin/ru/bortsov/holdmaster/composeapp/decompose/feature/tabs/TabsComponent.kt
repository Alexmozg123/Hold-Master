package ru.bortsov.holdmaster.composeapp.decompose.feature.tabs

import com.arkivanov.decompose.ComponentContext

class TabsComponent(
    componentContext: ComponentContext
): Tabs, ComponentContext by componentContext {

    class Factory : Tabs.Factory {
        override fun invoke(componentContext: ComponentContext): TabsComponent {
            return TabsComponent(componentContext = componentContext)
        }
    }
}