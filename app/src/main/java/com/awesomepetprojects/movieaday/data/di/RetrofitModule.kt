package com.awesomepetprojects.movieaday.data.di

import com.awesomepetprojects.movieaday.data.networking.apis.MoviesApi
import com.awesomepetprojects.movieaday.utils.BASE_URL
import com.awesomepetprojects.movieaday.utils.MOVIE_DB_API_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    single<Interceptor>(named("logging")) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    single<Interceptor>(named("headers")) {
        val ACCEPT_HEADER = "application/json"
        val AUTHORIZATION_HEADER = "Bearer $MOVIE_DB_API_KEY"

        Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("accept", ACCEPT_HEADER)
                .addHeader("Authorization", AUTHORIZATION_HEADER)
                .build()
            chain.proceed(request)
        }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named("logging")))
            .addInterceptor(get<Interceptor>(named("headers")))
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MoviesApi> {
        get<Retrofit>().create(MoviesApi::class.java)
    }
}