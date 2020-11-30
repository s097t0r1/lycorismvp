package com.s097t0r1.lycorismvp

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.s097t0r1.lycorismvp.di.DaggerAppComponent

class App : Application() {

    val appComponent = DaggerAppComponent.factory().create(this)

    private val cicerone = Cicerone.create()

    val router
        get() = cicerone.router

    val navigatorHolder
        get() = cicerone.getNavigatorHolder()


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: App
            private set
    }
}