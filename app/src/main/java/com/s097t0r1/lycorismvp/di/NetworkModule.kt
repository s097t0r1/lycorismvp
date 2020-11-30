package com.s097t0r1.lycorismvp.di

import com.s097t0r1.lycorismvp.model.source.remote.BASE_URL
import com.s097t0r1.lycorismvp.model.source.remote.UnsplashAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): UnsplashAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(UnsplashAPI::class.java)
    }
}