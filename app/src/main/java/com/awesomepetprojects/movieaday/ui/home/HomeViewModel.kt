package com.awesomepetprojects.movieaday.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import com.awesomepetprojects.movieaday.ui.home.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val coroutineContext = SupervisorJob() + Dispatchers.IO

    fun getTopRatedMovies() =
        moviesRepository.getMoviesByQuery(SimpleSQLiteQuery("SELECT * FROM movies"))

    fun refresh() = viewModelScope.launch(coroutineContext) {
        moviesRepository.deleteAllKeys()
        moviesRepository.deleteAllMovies()
    }
}