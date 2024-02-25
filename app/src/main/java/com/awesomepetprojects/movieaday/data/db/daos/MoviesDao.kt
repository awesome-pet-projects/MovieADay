package com.awesomepetprojects.movieaday.data.db.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.awesomepetprojects.movieaday.data.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): PagingSource<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<Movie>)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    fun getFilteredMovies(query: String): Flow<List<Movie>>
}