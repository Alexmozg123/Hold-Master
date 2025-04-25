package ru.bortsov.holdmaster.feature.auth.presentation.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.value.Value
import ru.bortsov.holdmaster.feature.auth.presentation.confirm.Confirm
import ru.bortsov.holdmaster.feature.auth.presentation.forgot.ForgotPassword
import ru.bortsov.holdmaster.feature.auth.presentation.login.Login
import ru.bortsov.holdmaster.feature.auth.presentation.singUp.SingUp

internal class AuthComponent(
    componentContext: ComponentContext,
    private val loginComponentFactory: Login.Factory,
    private val signUpComponentFactory: SingUp.Factory,
    private val forgotComponentFactory: ForgotPassword.Factory,
    private val confirmComponentFactory: Confirm.Factory,
) : Auth, ComponentContext by componentContext {

    private val _stackNav = StackNavigation<AuthConfig>()

    override val stack: Value<ChildStack<*, Auth.Child>> get() = _stack

    private val _stack = childStack(
        source = _stackNav,
        initialConfiguration = AuthConfig.Login,
        serializer = AuthConfig.serializer(),
        childFactory = ::childStackFactory,
    )

    override fun onBackClicked() = _stackNav.pop()

    override fun onBackClicked(toIndex: Int) = _stackNav.popTo(index = toIndex)

    private fun childStackFactory(
        config: AuthConfig,
        componentContext: ComponentContext
    ): Auth.Child = when (config) {
        AuthConfig.Confirm -> Auth.Child.ConfirmChild(confirmComponentFactory(componentContext))
        AuthConfig.Forgot -> Auth.Child.ForgotPasswordChild(forgotComponentFactory(componentContext))
        AuthConfig.Login -> Auth.Child.LoginChild(loginComponentFactory(componentContext))
        AuthConfig.SingUp -> Auth.Child.SingUpChild(signUpComponentFactory(componentContext))
    }

    class Factory(
        private val loginComponentFactory: Login.Factory,
        private val signUpComponentFactory: SingUp.Factory,
        private val forgotComponentFactory: ForgotPassword.Factory,
        private val confirmComponentFactory: Confirm.Factory,
    ) : Auth.Factory {
        override fun invoke(componentContext: ComponentContext): Auth {
            return AuthComponent(
                componentContext = componentContext,
                loginComponentFactory = loginComponentFactory,
                signUpComponentFactory = signUpComponentFactory,
                forgotComponentFactory = forgotComponentFactory,
                confirmComponentFactory = confirmComponentFactory,
            )
        }
    }
}