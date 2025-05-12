package ru.bortsov.holdmaster.composeapp.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import ru.bortsov.holdmaster.composeapp.decompose.feature.onboarding.Onboarding
import ru.bortsov.holdmaster.composeapp.decompose.feature.tabs.Tabs
import ru.bortsov.holdmaster.composeapp.decompose.splash.Splash
import ru.bortsov.holdmaster.composeapp.error.ErrorDialog
import ru.bortsov.holdmaster.feature.auth.presentation.navigation.Auth
import ru.bortsov.holdmaster.feature.photo.presentation.component.Photo

class RootComponent(
    componentContext: ComponentContext,
    private val splashComponentFactory: Splash.Factory,
    private val authComponentFactory: Auth.Factory,
    private val tabsComponentFactory: Tabs.Factory,
    private val onboardingComponentFactory: Onboarding.Factory,
    private val errorDialogFactory: ErrorDialog.Factory,
    private val takePhotoFactory: Photo.Factory,
) : Root, ComponentContext by componentContext {

    private val _stackNav = StackNavigation<RootConfig.Stack>()
    private val _slotNav = SlotNavigation<RootConfig.Slot>()

    private val _stack = childStack(
        source = _stackNav,
        initialConfiguration = RootConfig.Stack.Splash,
        serializer = RootConfig.Stack.serializer(),
        childFactory = ::childStackFactory,
    )

    private val _slot = childSlot(
        source = _slotNav,
        serializer = RootConfig.Slot.serializer(),
        childFactory = ::childSlotFactory,
    )

    override val stack: Value<ChildStack<*, Root.Child>>
        get() = _stack

    override val slot: Value<ChildSlot<*, Root.SlotChild>>
        get() = _slot

    override fun onBackClicked() = _stackNav.pop()

    override fun onBackClicked(toIndex: Int) = _stackNav.popTo(index = toIndex)

    override fun onDismiss() = _slotNav.dismiss()

    private fun childStackFactory(
        config: RootConfig.Stack,
        componentContext: ComponentContext
    ): Root.Child = when (config) {

        RootConfig.Stack.Splash -> {
            Root.Child.SplashChild(
                splashComponentFactory(
                    componentContext = componentContext,
                    navigateToMainFlowFeature = { _stackNav.replaceAll(RootConfig.Stack.Tabs) },
                    navigateToAuthFlowFeature = { _stackNav.replaceAll(RootConfig.Stack.Auth) },
                )
            )
        }

        RootConfig.Stack.Auth -> Root.Child.AuthChild(
            authComponentFactory(
                componentContext = componentContext,
                navigateToMain = { _stackNav.replaceAll(RootConfig.Stack.Tabs) },
                showError = { _slotNav.activate(RootConfig.Slot.ErrorDialog(it)) }
            )
        )


        RootConfig.Stack.Onboarding -> {
            Root.Child.OnboardingChild(onboardingComponentFactory(componentContext))
        }

        RootConfig.Stack.Tabs -> {
            Root.Child.TabsChild(tabsComponentFactory(componentContext))
        }

        RootConfig.Stack.TakePhoto -> {
            Root.Child.TakePhotoChild(takePhotoFactory(componentContext))
        }
    }

    private fun childSlotFactory(
        config: RootConfig.Slot,
        componentContext: ComponentContext
    ): Root.SlotChild = when (config) {
        is RootConfig.Slot.ErrorDialog -> Root.SlotChild.ErrorDialogChild(
            errorDialogFactory(
                componentContext = componentContext,
                onDismiss = { _slotNav.dismiss() },
                error = config.error
            )
        )
    }

    class Factory(
        private val splashComponentFactory: Splash.Factory,
        private val authComponentFactory: Auth.Factory,
        private val tabsComponentFactory: Tabs.Factory,
        private val onboardingComponentFactory: Onboarding.Factory,
        private val errorDialogFactory: ErrorDialog.Factory,
        private val takePhotoFactory: Photo.Factory,
    ) : Root.Factory {
        override fun invoke(componentContext: ComponentContext): RootComponent {
            return RootComponent(
                splashComponentFactory = splashComponentFactory,
                componentContext = componentContext,
                authComponentFactory = authComponentFactory,
                tabsComponentFactory = tabsComponentFactory,
                onboardingComponentFactory = onboardingComponentFactory,
                errorDialogFactory = errorDialogFactory,
                takePhotoFactory = takePhotoFactory,
            )
        }
    }
}