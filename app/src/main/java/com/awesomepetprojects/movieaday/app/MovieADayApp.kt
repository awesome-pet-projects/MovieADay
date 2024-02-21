package com.awesomepetprojects.movieaday.app

import android.app.Application
import com.awesomepetprojects.movieaday.data.di.datastoreModule
import com.awesomepetprojects.movieaday.data.di.homeModule
import com.awesomepetprojects.movieaday.data.di.retrofitModule
import com.awesomepetprojects.movieaday.data.di.roomModule
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
            modules(homeModule, retrofitModule, roomModule, datastoreModule)
        }
}