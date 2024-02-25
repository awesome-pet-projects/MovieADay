package com.awesomepetprojects.movieaday.ui.home.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.awesomepetprojects.movieaday.data.db.MovieADayDatabase
import com.awesomepetprojects.movieaday.data.models.MoviesType
import com.awesomepetprojects.movieaday.data.networking.mediator.MoviesMediator
import com.awesomepetprojects.movieaday.utils.MOVIES_TYPE_PREFERENCE
import com.awesomepetprojects.movieaday.utils.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoviesRepository : KoinComponent {

    private val datastore by inject<DataStore<Preferences>>()

    private val movieADayDatabase by inject<MovieADayDatabase>()
    private val moviesDao = movieADayDatabase.moviesDao()
    private val remoteKeysDao = movieADayDatabase.remoteKeysDao()

    @OptIn(ExperimentalPagingApi::class)
    fun getAllMovies(moviesType: MoviesType) = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE
        ), remoteMediator = MoviesMediator(moviesType)
    ) {
        moviesDao.getAllMovies()
    }.flow

    fun getInitialMoviesType(): Flow<String> =
        datastore.data.map { preferences ->
            preferences[MOVIES_TYPE_PREFERENCE] ?: MoviesType.TOP_RATED.toString()
        }

    suspend fun setInitialMoviesType(moviesType: MoviesType) =
        datastore.edit { preferences ->
            preferences[MOVIES_TYPE_PREFERENCE] = moviesType.toString()
        }

    suspend fun deleteAllKeys() = remoteKeysDao.deleteAllKeys()

    suspend fun deleteAllMovies() = moviesDao.deleteAllMovies()

    fun getFilteredMovies(query: String) = moviesDao.getFilteredMovies(query)
}