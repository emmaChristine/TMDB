package com.demo.movies.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.demo.movies.data.dto.Movie

// add movies, mark as favourite and query all of them.

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): MutableList<Movie>

    @Query("SELECT * FROM movies WHERE movies.id = :id")
    suspend fun getMovie(id: Int): Movie?

    @Insert(onConflict = IGNORE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = ABORT)
    suspend fun insertAllMovies(list :MutableList<Movie>)

    @Query("UPDATE movies SET isSaved=1 WHERE movies.id=:id")
    suspend fun markMovieAsFavourite(id: Int)

    @Query("SELECT * FROM movies WHERE movies.isSaved = 1 LIMIT :pageSize OFFSET((:pageIndex-1)*:pageSize) ")
    suspend fun getFavouriteMovies(pageSize: Int, pageIndex: Int): MutableList<Movie>
}