package com.s097t0r1.lycorismvp

import android.app.Application
import com.github.terrakok.cicerone.Router
import com.s097t0r1.lycorismvp.di.DatabaseModule
import com.s097t0r1.lycorismvp.di.NetworkModule
import com.s097t0r1.lycorismvp.di.RouterModule
import toothpick.Toothpick

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Toothpick.openScope(App::class)
            .installModules(
                DatabaseModule(this),
                NetworkModule(),
                RouterModule()
            )
    }
}