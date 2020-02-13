package com.demo.movies.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.movies.api.MoviesApi
import com.demo.movies.data.repository.MoviesRepository
import com.demo.movies.db.MovieDao
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class MoviesRepositoryTest {
    private val movieDao = Mockito.mock(MovieDao::class.java)
    private val mockService = Mockito.mock(MoviesApi::class.java)
    private val repo = MoviesRepository( movieDao, mockService)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun loadMoviesFromLocal() {
        runBlocking {
            repo.getFavouriteMoviesFromLocal()

            verify(movieDao).getFavouriteMovies(anyInt(), anyInt())
        }
    }

    @Test
    fun loadMoviesEmptyList() {
        runBlocking {
            `when`(movieDao.getAllMovies()).thenReturn(null)
        }

        runBlocking {
            repo.getPopularMoviesFromLocal()
        }

        verify(mockService, Mockito.never()).getPopularMovies()
    }
}