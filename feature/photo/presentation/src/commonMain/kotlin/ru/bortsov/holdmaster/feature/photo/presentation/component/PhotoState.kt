package ru.bortsov.holdmaster.feature.photo.presentation.component

import androidx.compose.ui.graphics.ImageBitmap
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.serialization.Serializable

@Serializable
data class PhotoState(
    val bitmap: ImageBitmap? = null,
) : InstanceKeeper.Instance
