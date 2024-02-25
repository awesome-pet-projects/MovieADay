package com.awesomepetprojects.movieaday.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.log
import androidx.paging.map
import androidx.room.Query
import com.awesomepetprojects.movieaday.data.models.Movie
import com.awesomepetprojects.movieaday.data.models.MoviesType
import com.awesomepetprojects.movieaday.ui.home.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val coroutineContext = SupervisorJob() + Dispatchers.IO

    private val moviesTypeFlow = MutableStateFlow(MoviesType.TOP_RATED)

    val searchQuery = MutableStateFlow("")

    init {
        setInitialMoviesType()
    }

    private fun setInitialMoviesType() =
        viewModelScope.launch(coroutineContext) {
            moviesRepository.getInitialMoviesType().collectLatest { moviesType ->
                moviesTypeFlow.value = MoviesType.valueOf(moviesType)
            }
        }

    fun setMoviesType(moviesType: MoviesType) =
        viewModelScope.launch(coroutineContext) {
            moviesTypeFlow.value = moviesType
            moviesRepository.setInitialMoviesType(moviesType)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val movies = moviesTypeFlow.flatMapLatest { movieType ->
        moviesRepository.getAllMovies(movieType)
    }

    fun getMovies() = movies

    private val filteredMovies = searchQuery.flatMapLatest { moviesRepository.getFilteredMovies(it) }

    fun getFilteredMovies() = filteredMovies

    fun refresh() = viewModelScope.launch(coroutineContext) {
        moviesRepository.deleteAllKeys()
        moviesRepository.deleteAllMovies()
    }
}