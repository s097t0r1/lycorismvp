package com.s097t0r1.lycorismvp.di

import com.s097t0r1.lycorismvp.model.source.remote.BASE_URL
import com.s097t0r1.lycorismvp.model.source.remote.UnsplashAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import toothpick.config.Module
import javax.inject.Singleton

class NetworkModule : Module() {

    init {
        bind(UnsplashAPI::class.java).toInstance(
            Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(UnsplashAPI::class.java)
        )
    }
}