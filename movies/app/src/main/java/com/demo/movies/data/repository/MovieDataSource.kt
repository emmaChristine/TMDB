package com.demo.movies.data.repository

import com.demo.movies.data.dto.Movie

/**
 * Main entry point for accessing movie data.
 */
interface MovieDataSource {

    suspend fun getPopularMovies(pageId: Int): MutableList<Movie>?

    suspend fun getPopularMovies(): MutableList<Movie>?

    suspend fun getMovieById(id: Int) : Movie?

    suspend fun savePopularMovies(list: MutableList<Movie>)

    suspend fun markMovieAsFavourite(id: Int)

    suspend fun getFavouriteMovies(): MutableList<Movie>?

}