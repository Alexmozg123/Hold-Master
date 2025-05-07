package ru.bortsov.holdmaster.composeapp.decompose.splash

import com.arkivanov.decompose.ComponentContext

interface Splash {

    fun onGoToPhotoFeatureClick()
    fun onGoToAuthFlowFeature()

    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            navigateToTakePhotoFeature: () -> Unit,
            navigateToAuthFlowFeature: () -> Unit,
        ) : Splash
    }
}