package com.demo.movies.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.demo.movies.TestUtils.DEFAULT_MOVIE_AVERAGE
import com.demo.movies.TestUtils.DEFAULT_MOVIE_ID
import com.demo.movies.TestUtils.DEFAULT_MOVIE_NAME
import com.demo.movies.TestUtils.DEFAUL_MOVIE_RELEASE_DATE
import com.demo.movies.TestUtils.movieDefault

import com.demo.movies.data.dto.Movie
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)

class DbTest {
    private lateinit var movieDao: MovieDao
    private lateinit var db: AppDatabase


    @Before fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieDao = db.movieDao()
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun emptyInitializedDb() {
        var list: List<Movie> ? = null
        runBlocking {
            list = db.movieDao().getAllMovies()
        }

        assertTrue(list!!.isEmpty())

        var favourites: List<Movie> ? = null
        runBlocking {
            favourites = db.movieDao().getFavouriteMovies(1, 10)
        }

        assertTrue(favourites!!.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadMovie() {

        runBlocking {
            db.movieDao().insert(movie = movieDefault)
        }

        var list: List<Movie> ? = null
        runBlocking {
            list = db.movieDao().getAllMovies()
        }

        assertNotNull(list)

        assertEquals(1, list?.size)
    }

    @Test
    @Throws(Exception::class)
    fun testGetMovieDetails() {
        runBlocking {
            db.movieDao().insert(movie = movieDefault)
        }


        var latestMovie: Movie? = null
        runBlocking {
            latestMovie = db.movieDao().getMovie(DEFAULT_MOVIE_ID)
        }

        assertNotNull(latestMovie)
        assertEquals(DEFAULT_MOVIE_ID, latestMovie?.id)
        assertEquals(DEFAULT_MOVIE_NAME, latestMovie?.title)
        assertEquals(DEFAULT_MOVIE_AVERAGE, latestMovie?.vote_average)
        assertEquals(DEFAUL_MOVIE_RELEASE_DATE, latestMovie?.release_date)
        assertEquals(false, latestMovie?.isSaved)
    }

    @Test
    @Throws(Exception::class)
    fun testMarkMovieAsFav() {
        runBlocking {
            db.movieDao().insert(movie = movieDefault)
        }

        runBlocking {
            db.movieDao().markMovieAsFavourite(DEFAULT_MOVIE_ID)
        }

        var list: List<Movie> ? = null
        runBlocking {
            list = db.movieDao().getAllMovies()
        }

        assertNotNull(list)
        assertEquals(1, list!!.size)
        assertEquals(DEFAULT_MOVIE_ID, list?.get(0)!!.id)
        assertEquals(DEFAULT_MOVIE_NAME, list?.get(0)!!.title)
        assertEquals(DEFAULT_MOVIE_AVERAGE, list?.get(0)!!.vote_average)
        assertEquals(DEFAUL_MOVIE_RELEASE_DATE, list?.get(0)!!.release_date)
        assertEquals(true, list?.get(0)!!.isSaved)
    }
}

