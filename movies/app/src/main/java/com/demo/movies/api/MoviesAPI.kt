package com.demo.movies.api

import com.demo.movies.data.dto.Movie
import com.demo.movies.data.dto.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//retrofit Network Interface for the Api
interface MoviesApi{
    @GET("movie/popular")
    fun getPopularMovies() : Deferred<Response<MovieResponse>>

    @GET("movie/popular/")
    fun getPopularMoviesPaginated(@Query("page") id:Int) : Deferred<Response<MovieResponse>>

    @GET("movie/{movie_id}")
    fun getMovieById(@Path("movie_id") id: Int): Deferred<Response<Movie>>
}

