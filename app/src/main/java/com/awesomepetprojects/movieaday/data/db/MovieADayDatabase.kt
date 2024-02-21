package com.awesomepetprojects.movieaday.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.awesomepetprojects.movieaday.data.db.converters.MovieEntityConverter
import com.awesomepetprojects.movieaday.data.db.daos.GenresDao
import com.awesomepetprojects.movieaday.data.db.daos.MoviesDao
import com.awesomepetprojects.movieaday.data.db.daos.RemoteKeysDao
import com.awesomepetprojects.movieaday.data.models.Genre
import com.awesomepetprojects.movieaday.data.models.Movie
import com.awesomepetprojects.movieaday.data.models.RemoteKey

@Database(
    entities = [Genre::class, Movie::class, RemoteKey::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(MovieEntityConverter::class)
abstract class MovieADayDatabase : RoomDatabase() {

    abstract fun genresDao(): GenresDao

    abstract fun moviesDao(): MoviesDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}