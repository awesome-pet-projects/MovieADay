package com.awesomepetprojects.movieaday.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieADayApp : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin(this)
    }

    private fun configureKoin(movieADayApp: MovieADayApp) =
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(movieADayApp)
            modules()
        }
}