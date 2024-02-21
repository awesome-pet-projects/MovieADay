package com.awesomepetprojects.movieaday.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awesomepetprojects.movieaday.data.models.converters.MoviesType
import com.awesomepetprojects.movieaday.ui.home.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val coroutineContext = SupervisorJob() + Dispatchers.IO

    fun getTopRatedMovies() =
        moviesRepository.getAllMovies(MoviesType.TOP_RATED)

    fun refresh() = viewModelScope.launch(coroutineContext) {
        moviesRepository.deleteAllKeys()
        moviesRepository.deleteAllMovies()
    }
}