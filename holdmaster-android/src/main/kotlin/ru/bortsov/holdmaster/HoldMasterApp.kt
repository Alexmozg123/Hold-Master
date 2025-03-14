package ru.bortsov.holdmaster

import android.app.Application
import ru.bortsov.holdmaster.composeapp.PlatformConfig
import ru.bortsov.holdmaster.composeapp.PlatformSDK

class HoldMasterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initPlatformSDK()
    }
}

fun HoldMasterApp.initPlatformSDK() =
    PlatformSDK.init(config = PlatformConfig(appContext = applicationContext))
