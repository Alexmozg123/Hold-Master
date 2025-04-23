package ru.bortsov.holdmaster.feature.photo.api

import android.graphics.Bitmap

interface PhotoPusher {
    suspend fun pushPhoto(photo: Bitmap)
}