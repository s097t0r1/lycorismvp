package com.s097t0r1.lycorismvp

import android.app.Application
import com.s097t0r1.lycorismvp.di.DaggerAppComponent

class App : Application() {

    val appComponent = DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }
}