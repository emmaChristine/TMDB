package com.demo.movies

import com.demo.movies.data.dto.Movie


object TestUtils {

    const val POSTER_DEFAULT = "test.jpg"
    const val DEFAULT_MOVIE_ID = 1
    const val DEFAULT_MOVIE_NAME = "Not another Final Destination"
    const val DEFAUL_MOVIE_RELEASE_DATE = "1.01.2020"
    const val DEFAULT_MOVIE_AVERAGE = 6.0

    val movieDefault = Movie(DEFAULT_MOVIE_ID, DEFAULT_MOVIE_NAME, DEFAUL_MOVIE_RELEASE_DATE, DEFAULT_MOVIE_AVERAGE, POSTER_DEFAULT, false)


}