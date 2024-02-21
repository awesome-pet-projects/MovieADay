package com.awesomepetprojects.movieaday.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieEntityConverter {

    @TypeConverter
    fun fromGenres(genres: List<String>?): String? =
        if (genres == null) null else Gson().toJson(genres)

    @TypeConverter
    fun toGenres(genresJson: String?): List<String>? =
        if (genresJson == null) null
        else Gson().fromJson(genresJson, object : TypeToken<List<String>?>() {}.type)

    @TypeConverter
    fun fromGenreIds(genreIds: List<Int>?): String? =
        if (genreIds == null) null else Gson().toJson(genreIds)

    @TypeConverter
    fun toGenreIds(genreIdsJson: String?): List<Int>? =
        if (genreIdsJson == null) null
        else Gson().fromJson(genreIdsJson, object : TypeToken<List<Int>?>() {}.type)
}