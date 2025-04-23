package ru.bortsov.holdmaster.composeapp.decompose.splash

import com.arkivanov.decompose.ComponentContext

interface Splash {

    fun onGoToPhotoFeatureClick()

    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            navigateToTakePhotoFeature: () -> Unit,
        ) : Splash
    }
}