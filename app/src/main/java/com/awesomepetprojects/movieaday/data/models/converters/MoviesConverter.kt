package com.awesomepetprojects.movieaday.data.models.converters

import com.awesomepetprojects.movieaday.data.models.Movie
import com.awesomepetprojects.movieaday.data.networking.dtos.TopRatedMoviesResponse
import com.awesomepetprojects.movieaday.utils.BASE_IMAGE_URL

fun TopRatedMoviesResponse.Result.toMovie() =
    Movie(
        null,
        this.id,
        this.genreIds,
        null,
        this.overview,
        "$BASE_IMAGE_URL$this.posterPath",
        this.releaseDate,
        this.title,
        this.voteAverage,
        this.voteCount,
    )

fun List<TopRatedMoviesResponse.Result>.toMovies() =
    this.map { result -> result.toMovie() }