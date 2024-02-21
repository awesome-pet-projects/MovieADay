package com.awesomepetprojects.movieaday.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awesomepetprojects.movieaday.data.models.MoviesType
import com.awesomepetprojects.movieaday.ui.home.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val coroutineContext = SupervisorJob() + Dispatchers.IO

    private val moviesTypeFlow = MutableStateFlow(MoviesType.TOP_RATED)

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
    val movies = moviesTypeFlow.flatMapLatest { movieType ->
        moviesRepository.getAllMovies(movieType)
    }

    fun refresh() = viewModelScope.launch(coroutineContext) {
        moviesRepository.deleteAllKeys()
        moviesRepository.deleteAllMovies()
    }
}