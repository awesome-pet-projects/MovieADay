package com.awesomepetprojects.movieaday.data.networking.dtos

import com.google.gson.annotations.SerializedName

data class SearchedMoviesResponse(
    @SerializedName("results")
    val results: List<Result>,
) {
    data class Result(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("video")
        val video: Boolean,
    )
}