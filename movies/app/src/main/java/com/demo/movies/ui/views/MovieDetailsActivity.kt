package com.demo.movies.ui.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.movies.R

internal class MovieDetailsActivity: AppCompatActivity() {

    // region Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
    }

    // endregion Activity
}