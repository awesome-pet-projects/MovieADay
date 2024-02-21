package com.awesomepetprojects.movieaday.data.networking.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.awesomepetprojects.movieaday.data.db.MovieADayDatabase
import com.awesomepetprojects.movieaday.data.models.Movie
import com.awesomepetprojects.movieaday.data.models.RemoteKey
import com.awesomepetprojects.movieaday.data.models.converters.MoviesType
import com.awesomepetprojects.movieaday.data.models.converters.toGenres
import com.awesomepetprojects.movieaday.data.models.converters.toMovies
import com.awesomepetprojects.movieaday.data.networking.apis.MoviesApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(ExperimentalPagingApi::class)
class MoviesMediator(
    private val moviesType: MoviesType
) : RemoteMediator<Int, Movie>(), KoinComponent {

    private val moviesApi by inject<MoviesApi>()

    private val movieADayDatabase by inject<MovieADayDatabase>()
    private val moviesDao = movieADayDatabase.moviesDao()
    private val genresDao = movieADayDatabase.genresDao()
    private val remoteKeysDao = movieADayDatabase.remoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {
        try {
            val page: Int = when (loadType) {
                LoadType.APPEND -> remoteKeysDao.getNextPage()
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.REFRESH -> 1
            }

            makeGenresApiCall()
            makeMoviesApiCall(page)

            return MediatorResult.Success(false)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun makeMoviesApiCall(
        page: Int,
    ) {
        val response = when(moviesType) {
            MoviesType.TOP_RATED -> moviesApi.getTopRatedMovies(page)
            MoviesType.POPULAR -> moviesApi.getPopularMovies(page)
            MoviesType.NOW_PLAYING -> moviesApi.getNowPlayingMovies(page)
            MoviesType.UPCOMING -> moviesApi.getUpcomingMovies(page)
        }
        val responseBody = response.body()

        if (response.isSuccessful && responseBody != null) {
            val movies = responseBody.results.toMovies()
            insertKeyAndMovies(movies, page)
        }
    }

    private suspend fun insertKeyAndMovies(
        movies: List<Movie>,
        page: Int
    ) {
        movieADayDatabase.withTransaction {
            val updatedMovies = updateMovies(movies)
            moviesDao.insertAllMovies(updatedMovies)
            remoteKeysDao.insertKey(RemoteKey(nextPage = page + 1))
        }
    }

    private suspend fun updateMovies(movies: List<Movie>) = movies.map { movie ->
        val genres = getGenresByIds(movie.genreIds)
        movie.copy(genres = genres)
    }

    private suspend fun getGenresByIds(genreIds: List<Int>) =
        genresDao.getMovieGenresByIds(genreIds)

    private suspend fun makeGenresApiCall() {
        val response = moviesApi.getAllGenres()
        val responseBody = response.body()

        if (response.isSuccessful && responseBody != null) {
            val genres = responseBody.genres.toGenres()
            genresDao.insertAllGenres(genres)
        }
    }
}