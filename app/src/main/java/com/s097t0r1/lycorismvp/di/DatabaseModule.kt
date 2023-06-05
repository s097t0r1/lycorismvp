package com.s097t0r1.lycorismvp.di

import android.content.Context
import com.s097t0r1.lycorismvp.model.source.local.PhotoDAO
import com.s097t0r1.lycorismvp.model.source.local.PhotoDatabase
import toothpick.config.Module

class DatabaseModule(
    private val context: Context
) : Module() {

    init {
        bind(PhotoDAO::class.java)
            .toInstance(PhotoDatabase.getInstance(context).photoDAO())
    }

}