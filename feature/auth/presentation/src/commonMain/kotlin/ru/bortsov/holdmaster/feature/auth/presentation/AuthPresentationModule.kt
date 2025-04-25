package ru.bortsov.holdmaster.feature.auth.presentation

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bortsov.holdmaster.feature.auth.presentation.confirm.Confirm
import ru.bortsov.holdmaster.feature.auth.presentation.confirm.ConfirmComponent
import ru.bortsov.holdmaster.feature.auth.presentation.forgot.ForgotPassword
import ru.bortsov.holdmaster.feature.auth.presentation.forgot.ForgotPasswordComponent
import ru.bortsov.holdmaster.feature.auth.presentation.login.Login
import ru.bortsov.holdmaster.feature.auth.presentation.login.LoginComponent
import ru.bortsov.holdmaster.feature.auth.presentation.navigation.Auth
import ru.bortsov.holdmaster.feature.auth.presentation.navigation.AuthComponent
import ru.bortsov.holdmaster.feature.auth.presentation.singUp.SingUp
import ru.bortsov.holdmaster.feature.auth.presentation.singUp.SingUpComponent

val authPresentationModule: Module = module {
    singleOf(LoginComponent::Factory) bind Login.Factory::class
    singleOf(SingUpComponent::Factory) bind SingUp.Factory::class
    singleOf(ConfirmComponent::Factory) bind Confirm.Factory::class
    singleOf(ForgotPasswordComponent::Factory) bind ForgotPassword.Factory::class
    singleOf(AuthComponent::Factory) bind Auth.Factory::class
}