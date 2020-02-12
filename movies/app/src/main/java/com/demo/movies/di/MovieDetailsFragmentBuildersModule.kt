package com.demo.movies.di

import com.demo.movies.ui.views.MovieDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieDetailsFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeMovieDetailsFragment(): MovieDetailsFragment
}