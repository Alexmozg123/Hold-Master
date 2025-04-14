package ru.bortsov.holdmaster.feature.photo.presentation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

interface Photo {

    val state: Value<PhotoState>

    fun onTakePhotoClick()

    interface Factory {
        operator fun invoke(componentContext: ComponentContext) : Photo
    }
}