package ru.bortsov.holdmaster.core.base.settings

import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module

internal val settingsModule: Module = module {
    val settings: ObservableSettings = Settings() as ObservableSettings
    single<ObservableSettings> { settings }
    single<Settings> { settings }
}