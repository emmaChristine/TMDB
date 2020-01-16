package com.demo.movies.data.repository

import com.demo.movies.data.dto.Movie
import com.demo.movies.data.local.dao.MovieDao
import com.demo.movies.data.remote.MoviesApi

class MoviesRepository(val api : MoviesApi, val movieDao: MovieDao) : BaseRepository() {


     suspend fun getPopularMovies() : MutableList<Movie>?{


//         if (isNetworkConnected(mov)) {
             val moviesResponse = safeApiCall(
                 call = {api.getPopularMovies().await()},
                 errorMessage = "Error Fetching Popular Movies"
             )

             return moviesResponse?.results?.toMutableList()
//         }



    }

    suspend fun getPopularMoviesPaginated(pageId: Int): MutableList<Movie>?{

        val moviesResponse = safeApiCall(
            call = {api.getPopularMoviesPaginated(pageId).await()},
            errorMessage = "Error Fetching Popular Movies"
        )

        return moviesResponse?.results?.toMutableList()

    }

    suspend fun getMovieById(id: Int) : Movie? {

        return safeApiCall(
        call = { api.getMovieById(id).await()},
        errorMessage = "Error Fetching Movie details"
    )

    }

    // from local repository

    suspend fun savePopularMoviesToLocal(list: MutableList<Movie>) {

        if (movieDao.getAllMovies().isEmpty()) {
            movieDao.insertAllMovies(list)
        }
    }

    suspend fun getPopularMoviesFromLocal(): MutableList<Movie>?{
       return movieDao.getAllMovies()
    }

    suspend fun getMovieByIdFromLocal(id: Int): Movie? {
        return movieDao.getMovie(id)

    }

    suspend fun markMovieAsFavouriteToLocal(id: Int) {
        movieDao.markMovieAsFavourite(id)
    }

    suspend fun getFavouriteMoviesFromLocal(): MutableList<Movie>? {
        return movieDao.getFavouriteMovies(20, 1)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: MoviesRepository? = null

        fun getInstance(api: MoviesApi, movieDao: MovieDao) =
            instance ?: synchronized(this) {
                instance ?: MoviesRepository(api, movieDao).also { instance = it }
            }
    }



}
