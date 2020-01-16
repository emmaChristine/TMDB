package com.demo.movies.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey val id: Int,
    val title: String,
    val release_date: String,
    val vote_average: Double,
    val poster_path: String  = "",
    val isSaved: Boolean = false) {

    override fun toString() =
        javaClass.simpleName + " - id:" + id + ", title:" + title + ", release date:" + release_date + ", vote average:" + vote_average + ", poster path:" + poster_path
}


// Data Model for the Response returned from API
data class MovieResponse(
    val results: List<Movie>
)

