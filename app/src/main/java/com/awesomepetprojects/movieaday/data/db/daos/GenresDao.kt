package com.awesomepetprojects.movieaday.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.awesomepetprojects.movieaday.data.models.Genre

@Dao
interface GenresDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllGenres(genres: List<Genre>)

    @Query("SELECT name FROM genres WHERE id IN (:genresIds)")
    suspend fun getMovieGenresByIds(genresIds: List<Int>): List<String>
}