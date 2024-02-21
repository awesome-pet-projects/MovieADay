package com.awesomepetprojects.movieaday.data.networking.apis

import com.awesomepetprojects.movieaday.data.networking.dtos.GenresResponse
import com.awesomepetprojects.movieaday.data.networking.dtos.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page")
        page: Int
    ): Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page")
        page: Int
    ): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page")
        page: Int
    ): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page")
        page: Int
    ): Response<MoviesResponse>

    @GET("genre/movie/list")
    suspend fun getAllGenres(): Response<GenresResponse>

    @GET("search/movie")
    suspend fun getSearchedMovies(
        @Query("query")
        query: String,
        @Query("page")
        page: Int,
        @Query("include_adult")
        isIncludeAdult: Boolean
    ): Response<MoviesResponse>
}