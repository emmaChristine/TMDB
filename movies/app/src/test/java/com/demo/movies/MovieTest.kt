package com.demo.movies

import com.demo.movies.TestUtils.POSTER_DEFAULT
import com.demo.movies.TestUtils.movieDefault
import com.demo.movies.data.dto.Movie
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class MovieTest {

    private lateinit var movie: Movie

    @Before
    fun setUp() {
        movie = movieDefault
    }

    @Test
    fun test_default_values() {
        Assert.assertEquals(1, movie.id)
        Assert.assertEquals("Not another Final Destination", movie.title)
        Assert.assertEquals("1.01.2020", movie.release_date)
        Assert.assertEquals(6.0, movie.vote_average, 0.1 )
        Assert.assertEquals(POSTER_DEFAULT, movie.poster_path)
        Assert.assertEquals(false, movie.isSaved)
    }

    @Test
    fun test_toString() {
        Assert.assertNotNull(movie.toString())
    }
}