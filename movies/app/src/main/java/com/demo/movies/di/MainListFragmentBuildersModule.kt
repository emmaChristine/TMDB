package com.demo.movies.di

import com.demo.movies.ui.views.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainListFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeMoviesFragment(): MoviesFragment

}

