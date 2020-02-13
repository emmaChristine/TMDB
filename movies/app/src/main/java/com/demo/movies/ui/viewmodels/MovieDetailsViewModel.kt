package com.demo.movies.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import com.demo.movies.data.dto.Movie
import com.demo.movies.data.repository.MoviesRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieDetailsViewModel
@Inject constructor(val repository: MoviesRepository) : BaseViewModel() {

    var movieId: Int = 0

    // region logic

    val movieDetailsLiveData = MutableLiveData<Movie>()

    fun fetchMovieDetails(id: Int) {
        movieId = id

        scope.launch {
            var movieDetails: Movie? =
                repository.getMovieByIdFromRemote(id)

            // fallback from remote to local
            if  (movieDetails == null) {
                movieDetails = repository.getMovieByIdFromLocal(id)
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