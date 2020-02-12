package com.demo.movies.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.demo.movies.TestUtil.movieDefault
import com.demo.movies.data.dto.Movie
import junit.framework.Assert.assertTrue
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

        movieDao.insertAllMovies(listOf(movieDefault) as MutableList<Movie>)
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
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


        assertTrue(list!!.isNotEmpty())
    }
}