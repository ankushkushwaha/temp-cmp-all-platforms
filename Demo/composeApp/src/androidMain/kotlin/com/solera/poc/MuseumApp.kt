package com.solera.poc

import android.app.Application
import android.content.Context
import com.jetbrains.kmpapp.di.initKoin

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        instance = this
    }

    companion object {
        private lateinit var instance: MuseumApp

        fun getContext(): Context {
            return instance.applicationContext
        }
    }
}
