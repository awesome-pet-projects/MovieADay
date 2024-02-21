package com.awesomepetprojects.movieaday.data.models.converters

import com.awesomepetprojects.movieaday.data.models.Genre
import com.awesomepetprojects.movieaday.data.networking.dtos.GenresResponse

fun GenresResponse.Genre.toGenre() =
    Genre(
        this.id,
        this.name,
    )

fun List<GenresResponse.Genre>.toGenres() =
    this.map { it.toGenre() }