package com.demo.movies.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.demo.movies.data.dto.Movie
import com.demo.movies.ui.utils.isNetworkConnected
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailsViewModel(application: Application): BaseViewModel(application) {

    // region logic

    val movieDetailsLiveData = MutableLiveData<Movie>()

    fun fetchMovieDetails(id: Int) {
        scope.launch {
            val movieDetails: Movie? = if (isNetworkConnected(getApplication())) {
                repository.getMovieById(id)
            }
            else {
                repository.getMovieByIdFromLocal(id)
            }

            if (movieDetails != null) {
                Timber.d("Finished loading movie details for: $movieDetails")
            }
            else {
                Timber.e("Cannot load movie")
            }

            movieDetailsLiveData.postValue(movieDetails)
        }
    }

    fun markMovieAsfavourite(id: Int) {
        scope.launch {
            repository.markMovieAsFavouriteToLocal(id)
        }
    }

    // endregion logic
}