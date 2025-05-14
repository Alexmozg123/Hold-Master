package ru.bortsov.holdmaster.composeapp.splash

import com.arkivanov.decompose.ComponentContext

interface Splash {

    interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            navigateToMainFlowFeature: () -> Unit,
            navigateToAuthFlowFeature: () -> Unit,
        ) : Splash
    }
}