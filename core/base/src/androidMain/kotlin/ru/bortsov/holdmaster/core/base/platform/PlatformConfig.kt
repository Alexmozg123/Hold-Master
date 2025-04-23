package ru.bortsov.holdmaster.core.base.platform

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

public actual class PlatformConfig(public val appContext: Context) {
    public var photoLauncher: ActivityResultLauncher<Intent>? = null
}