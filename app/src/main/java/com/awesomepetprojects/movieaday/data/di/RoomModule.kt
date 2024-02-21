package com.awesomepetprojects.movieaday.data.di

import androidx.room.Room
import com.awesomepetprojects.movieaday.data.db.MovieADayDatabase
import com.awesomepetprojects.movieaday.data.db.daos.GenresDao
import com.awesomepetprojects.movieaday.data.db.daos.MoviesDao
import com.awesomepetprojects.movieaday.data.db.daos.RemoteKeysDao
import com.awesomepetprojects.movieaday.utils.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {

    single<MovieADayDatabase> {
        Room.databaseBuilder(
            androidContext(),
            MovieADayDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    factory<MoviesDao> {
        get<MovieADayDatabase>().moviesDao()
    }

    factory<GenresDao> {
        get<MovieADayDatabase>().genresDao()
    }

    factory<RemoteKeysDao> {
        get<MovieADayDatabase>().remoteKeysDao()
    }
}