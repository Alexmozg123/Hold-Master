package ru.bortsov.holdmaster.composeapp.tabs.ui

import org.jetbrains.compose.resources.DrawableResource

internal data class BottomTab(
    val icon: DrawableResource,
    val onClick: () -> Unit,
    val isSelected: Boolean
)