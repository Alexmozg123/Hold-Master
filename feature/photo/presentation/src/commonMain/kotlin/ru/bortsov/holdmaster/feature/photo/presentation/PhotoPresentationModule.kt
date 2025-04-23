package ru.bortsov.holdmaster.feature.photo.presentation

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.bortsov.holdmaster.feature.photo.presentation.component.Photo
import ru.bortsov.holdmaster.feature.photo.presentation.component.PhotoComponent

val photoPresentationModule = module {
    singleOf(PhotoComponent::Factory) bind Photo.Factory::class
}