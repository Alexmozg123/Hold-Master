package ru.bortsov.holdmaster.feature.auth.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import ru.bortsov.holdmaster.core.utils.RootError
import ru.bortsov.holdmaster.feature.auth.presentation.login.Login
import ru.bortsov.holdmaster.feature.auth.presentation.singUp.SingUp
import ru.bortsov.holdmaster.feature.auth.presentation.forgot.ForgotPassword
import ru.bortsov.holdmaster.feature.auth.presentation.confirm.Confirm

interface Auth : BackHandlerOwner {

    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        internal class LoginChild(val component: Login) : Child()
        internal class SingUpChild(val component: SingUp) : Child()
        internal class ForgotPasswordChild(val component: ForgotPassword) : Child()
        internal class ConfirmChild(val component: Confirm) : Child()
    }

    fun interface Factory {
        operator fun invoke(
            componentContext: ComponentContext,
            navigateToMain: () -> Unit,
            showError: (RootError) -> Unit,
        ): Auth
    }
}