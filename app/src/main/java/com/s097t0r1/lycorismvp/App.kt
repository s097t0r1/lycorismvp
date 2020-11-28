package com.s097t0r1.lycorismvp

import android.app.Application
import com.s097t0r1.lycorismvp.di.DaggerAppComponent

class App : Application() {
    val appComponent = DaggerAppComponent.factory().create(this)

    companion object {
        var share = this
        private set
    }
}