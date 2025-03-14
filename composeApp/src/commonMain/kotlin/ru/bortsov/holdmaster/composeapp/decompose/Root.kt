package ru.bortsov.holdmaster.composeapp.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import ru.bortsov.holdmaster.composeapp.decompose.feature.auth.Auth
import ru.bortsov.holdmaster.composeapp.decompose.feature.onboarding.Onboarding
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.Tabs
import ru.bortsov.holdmaster.composeapp.error.ErrorDialog

interface Root: BackHandlerOwner {

    val stack: Value<ChildStack<*, Child>>
    val slot: Value<ChildSlot<*, SlotChild>>

    fun onBackClicked()
    fun onBackClicked(toIndex: Int)
    fun onDismiss()

    sealed class Child {
        data object SplashChild : Child()
        class TabsChild(val component: Tabs) : Child()
        class AuthChild(val component: Auth) : Child()
        class OnboardingChild(val component: Onboarding) : Child()
    }

    sealed class SlotChild {
        data class ErrorDialogChild(val component: ErrorDialog) : SlotChild()
    }

    fun interface Factory {
        operator fun invoke(componentContext: ComponentContext): RootComponent
    }
}