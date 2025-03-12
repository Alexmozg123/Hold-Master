package ru.bortsov.holdmaster

import android.app.Application
import ru.bortsov.holdmaster.composeapp.KoinStarter

class HoldMasterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinStarter(context = applicationContext).init()
    }
}
