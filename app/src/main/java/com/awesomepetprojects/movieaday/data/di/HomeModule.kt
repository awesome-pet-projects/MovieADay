package com.awesomepetprojects.movieaday.data.di

import com.awesomepetprojects.movieaday.ui.home.HomeViewModel
import com.awesomepetprojects.movieaday.ui.home.repository.MoviesRepository
import org.koin.dsl.module

val homeModule = module {

    single<MoviesRepository> {
        MoviesRepository()
    }

    factory<HomeViewModel> {
        HomeViewModel(get())
    }
}