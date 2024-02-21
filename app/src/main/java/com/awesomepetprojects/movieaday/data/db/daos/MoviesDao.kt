package com.awesomepetprojects.movieaday.data.db.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import com.awesomepetprojects.movieaday.data.models.Movie

@Dao
interface MoviesDao {

    @RawQuery
    fun getSortedMovies(query: Query): PagingSource<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<Movie>)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()
}