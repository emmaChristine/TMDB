package com.demo.movies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.movies.viewmodels.MainViewModel
import com.demo.movies.viewmodels.MovieDetailsViewModel
import com.demo.movies.viewmodels.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(viewmodel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class )
    abstract fun bindMovieDetailsViewModel(mainViewModel: MovieDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelsFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
