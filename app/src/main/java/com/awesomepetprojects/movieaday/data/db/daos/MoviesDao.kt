package com.awesomepetprojects.movieaday.data.db.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import com.awesomepetprojects.movieaday.data.models.Movie

@Dao
interface MoviesDao {

    @RawQuery(observedEntities = [Movie::class])
    fun getMoviesByQuery(query: SimpleSQLiteQuery): PagingSource<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<Movie>)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()
}