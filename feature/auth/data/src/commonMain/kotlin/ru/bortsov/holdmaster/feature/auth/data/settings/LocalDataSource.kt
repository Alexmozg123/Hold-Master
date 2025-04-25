package ru.bortsov.holdmaster.feature.auth.data.settings

import com.russhwolf.settings.Settings

internal class LocalDataSource(private val settings: Settings) {
    fun clearAllData() { settings.clear() }
}