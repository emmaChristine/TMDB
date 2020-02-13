package com.demo.movies.dto

import com.demo.movies.TestUtils.DEFAULT_MOVIE_AVERAGE
import com.demo.movies.TestUtils.DEFAULT_MOVIE_NAME
import com.demo.movies.TestUtils.DEFAUL_MOVIE_RELEASE_DATE
import com.demo.movies.TestUtils.POSTER_DEFAULT
import com.demo.movies.TestUtils.movieDefault
import com.demo.movies.data.dto.Movie
import com.demo.movies.data.repository.MoviesRepository
import com.demo.movies.ui.viewmodels.MovieViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.mock

class MovieTest {

    private lateinit var movie: Movie

    @Before
    fun setUp() {
        movie = movieDefault
    }

    @After
    fun tearDown() {}

    @Test
    fun test_default_values() {
        Assert.assertEquals(1, movie.id)
        Assert.assertEquals(DEFAULT_MOVIE_NAME, movie.title)
        Assert.assertEquals(DEFAUL_MOVIE_RELEASE_DATE, movie.release_date)
        Assert.assertEquals(DEFAULT_MOVIE_AVERAGE, movie.vote_average, 0.1 )
        Assert.assertEquals(POSTER_DEFAULT, movie.poster_path)
        Assert.assertEquals(false, movie.isSaved)
    }

    @Test
    fun test_toString() {
        Assert.assertNotNull(movie.toString())
    }
}