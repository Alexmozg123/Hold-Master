package ru.bortsov.holdmaster.feature.photo.api

import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun takePhoto()
    fun getPhoto(): Flow<ByteArray>
}