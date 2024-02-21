package com.awesomepetprojects.movieaday.utils

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

const val MOVIE_DB_API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZjMxNmU5ODJkNmVkNWY0ZjQ1ZjJkMWE4ZTdhYWVmOSIsInN1YiI6IjY1YzUyNjdkMWI3MGFlMDE4NGEyMjYzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.G8cp1IiPUz9Irna3KsN3Vbn3TVcLWZerLoLjSvEzO0Y"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
const val DATABASE_NAME = "database"
const val PAGE_SIZE = 20
const val MOVIES_PREFERENCES = "movies_preferences"

val MOVIES_TYPE_PREFERENCE = stringPreferencesKey("movies_type")