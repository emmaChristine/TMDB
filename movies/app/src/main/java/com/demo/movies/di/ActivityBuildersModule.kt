package com.demo.movies.di

import com.demo.movies.ui.views.MovieDetailsActivity
import com.demo.movies.ui.views.MoviesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainListFragmentBuildersModule::class])
    abstract fun contributeMoviesActivity(): MoviesActivity

    @ContributesAndroidInjector(modules  = [MovieDetailsFragmentBuildersModule::class])
    abstract fun contributeMovieDetailsActivity(): MovieDetailsActivity
}