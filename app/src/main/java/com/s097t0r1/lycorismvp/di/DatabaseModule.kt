package com.s097t0r1.lycorismvp.di

import android.content.Context
import com.s097t0r1.lycorismvp.model.source.local.PhotoDAO
import com.s097t0r1.lycorismvp.model.source.local.PhotoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDAO(context: Context): PhotoDAO =
        PhotoDatabase.getInstance(context).photoDAO()
}