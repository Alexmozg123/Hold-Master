package ru.bortsov.holdmaster.composeapp.decompose.splash

import com.arkivanov.decompose.ComponentContext

internal class SplashComponent(
    componentContext: ComponentContext,
    private val navigateToTakePhotoFeature: () -> Unit,
) : Splash, ComponentContext by componentContext {

    override fun onGoToPhotoFeatureClick() = navigateToTakePhotoFeature()

    class Factory : Splash.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            navigateToTakePhotoFeature: () -> Unit
        ): Splash {
            return SplashComponent(
                componentContext = componentContext,
                navigateToTakePhotoFeature = navigateToTakePhotoFeature,
            )
        }
    }
}