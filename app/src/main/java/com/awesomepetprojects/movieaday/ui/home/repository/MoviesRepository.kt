package com.awesomepetprojects.movieaday.ui.home.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.awesomepetprojects.movieaday.data.db.MovieADayDatabase
import com.awesomepetprojects.movieaday.data.models.converters.MoviesType
import com.awesomepetprojects.movieaday.data.networking.mediator.MoviesMediator
import com.awesomepetprojects.movieaday.utils.PAGE_SIZE
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MoviesRepository : KoinComponent {

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

    suspend fun deleteAllKeys() = remoteKeysDao.deleteAllKeys()

    suspend fun deleteAllMovies() = moviesDao.deleteAllMovies()
}