package com.necs.marveltp

import android.app.Application
import com.necs.marveltp.ui.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MarvelApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MarvelApp)
        }
    }
}