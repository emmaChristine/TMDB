package com.demo.movies.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.demo.movies.data.dto.Movie
import com.demo.movies.ui.utils.isNetworkConnected
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel(application: Application) : BaseViewModel(application) {

    companion object {
        const val PAGE_SIZE = 20
        const val FIRST_PAGE = 1
    }


    var popularMoviesLiveData = MutableLiveData<MutableList<Movie>>()

    // region logic


    fun fetchPopularMoviesPaginated(pageId: Int) {
        scope.launch {
            val popularMovies: MutableList<Movie>? = if (isNetworkConnected(getApplication())) {
                repository.getPopularMoviesPaginated(pageId)
            } else {
                repository.getPopularMoviesFromLocal()
            }

            if (popularMovies != null) {
                Timber.d("Finished loading " + popularMovies.count() + " movies")

                popularMovies.forEach{ movie ->
                    Timber.d("Movie $movie")
                }
            }
            else {
                Timber.e("No movies were loaded")
            }

            popularMoviesLiveData.postValue(popularMovies)
        }
    }


    fun storePopularMovies(list: MutableList<Movie>) {
        scope.launch {
            repository.savePopularMoviesToLocal(list)
        }
    }

    // endregion logic

    // region Pagination - in progress

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPageSize(PAGE_SIZE)
        .build()


     inner class MovieDataSource : PageKeyedDataSource<Int, Movie>() {

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

            fetchPopularMoviesPaginated(params.key)
            val key = if (params.key > 1) params.key - 1 else 0
        }

        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Movie>
        ) {

            fetchPopularMoviesPaginated(FIRST_PAGE)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
            fetchPopularMoviesPaginated(params.key)
            val key = params.key + 1
        }
    }

    // endregion pagination
}


