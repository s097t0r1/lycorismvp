package com.s097t0r1.lycorismvp.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import toothpick.config.Module


class RouterModule : Module() {

    init {

        val cicerone = Cicerone.create()

        bind(Cicerone::class.java)
            .toInstance(cicerone)

        bind(Router::class.java)
            .toInstance(cicerone.router)

        bind(NavigatorHolder::class.java)
            .toInstance(cicerone.getNavigatorHolder())

    }
}
//@Module
//object RouterModule {
//
//    @Singleton
//    @Provides
//    fun provideCicerone(): Cicerone<Router> {
//        return Cicerone.create()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRouter(cicerone: Cicerone<Router>): Router {
//        return cicerone.router
//    }
//
//    @Singleton
//    @Provides
//    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
//        return cicerone.getNavigatorHolder()
//    }
//
//}