package ru.bortsov.holdmaster

import android.app.Application
import ru.bortsov.holdmaster.composeapp.PlatformSDK
import ru.bortsov.holdmaster.core.base.platform.PlatformConfig

class HoldMasterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initPlatformSDK()
    }
}

fun HoldMasterApp.initPlatformSDK() =
    PlatformSDK.init(config = PlatformConfig(appContext = applicationContext))
