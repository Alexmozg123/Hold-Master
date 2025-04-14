package ru.bortsov.holdmaster.feature.photo.api

import kotlinx.coroutines.flow.Flow

interface CameraManager {
    fun photoFlow(): Flow<ByteArray>
}