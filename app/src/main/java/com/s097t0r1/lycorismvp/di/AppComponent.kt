package com.s097t0r1.lycorismvp.di

import android.content.Context
import com.s097t0r1.lycorismvp.ui.feed.FeedFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, NetworkModule::class, RouterModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: FeedFragment)
}