package ru.bortsov.holdmaster.composeapp.decompose.splash

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.bortsov.holdmaster.core.utils.componentCoroutineScope
import ru.bortsov.holdmaster.feature.auth.api.AuthRepository
import ru.bortsov.holdmaster.feature.auth.api.model.AuthState

internal class SplashComponent(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val navigateToMainFlowFeature: () -> Unit,
    private val navigateToAuthFlowFeature: () -> Unit,
) : Splash, ComponentContext by componentContext {

    private val splashScope = componentCoroutineScope()

    init { checkAuthStatus() }

    private fun checkAuthStatus() {
        splashScope.launch(Dispatchers.IO) {
            authRepository.authState.collect {
                when (it) {
                    AuthState.Authorized -> withContext(Dispatchers.Main) {
                        navigateToMainFlowFeature()
                    }
                    AuthState.NotAuthorized -> withContext(Dispatchers.Main) {
                        navigateToAuthFlowFeature()
                    }
                }
            }
        }
    }

    class Factory(
        private val authRepository: AuthRepository,
    ) : Splash.Factory {
        override fun invoke(
            componentContext: ComponentContext,
            navigateToMainFlowFeature: () -> Unit,
            navigateToAuthFlowFeature: () -> Unit,
        ): Splash {
            return SplashComponent(
                componentContext = componentContext,
                authRepository = authRepository,
                navigateToMainFlowFeature = navigateToMainFlowFeature,
                navigateToAuthFlowFeature = navigateToAuthFlowFeature,
            )
        }
    }
}