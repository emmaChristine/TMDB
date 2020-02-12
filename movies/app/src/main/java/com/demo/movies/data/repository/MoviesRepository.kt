package com.demo.movies.data.repository

import com.demo.movies.data.dto.Movie
import com.demo.movies.db.MovieDao
import com.demo.movies.api.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesRepository @Inject constructor(
    val movieDao: MovieDao,
    val api: MoviesApi
): BaseRepository() {

    suspend fun getPopularMovies(pageId: Int): MutableList<Movie>? = withContext(Dispatchers.IO) {

        val result = getPopularMoviesPaginatedFromRemote(pageId)

        if (result.isNullOrEmpty()) {
            return@withContext getPopularMoviesFromLocal()
        }
        else
            return@withContext result
    }

    suspend fun getPopularMoviesPaginatedFromRemote(pageId: Int): MutableList<Movie>? = withContext(Dispatchers.IO) {

        val moviesResponse = safeApiCall(
            call = {api.getPopularMoviesPaginated(pageId).await()},
            errorMessage = "Error Fetching Popular Movies")

        return@withContext moviesResponse?.results?.toMutableList()

    }

    suspend fun getMovieByIdFromRemote(id: Int) : Movie? = withContext(Dispatchers.IO) {

        return@withContext safeApiCall(
            call = { api.getMovieById(id).await()},
            errorMessage = "Error Fetching Movie details")

    }

    // from local repository

    suspend fun savePopularMoviesToLocal(list: MutableList<Movie>) = withContext(Dispatchers.IO){

        if (movieDao.getAllMovies().isEmpty()) {
            movieDao.insertAllMovies(list)
        }
    }

    suspend fun getPopularMoviesFromLocal(): MutableList<Movie>? = withContext(Dispatchers.IO){
       return@withContext movieDao.getAllMovies()
    }

    suspend fun getMovieByIdFromLocal(id: Int): Movie? = withContext(Dispatchers.IO) {
        return@withContext movieDao.getMovie(id)

    }

    suspend fun markMovieAsFavouriteToLocal(id: Int) = withContext(Dispatchers.IO) {
        movieDao.markMovieAsFavourite(id)
    }

    suspend fun getFavouriteMoviesFromLocal(): MutableList<Movie>? = withContext(Dispatchers.IO) {
        return@withContext movieDao.getFavouriteMovies(20, 1)
    }


}
