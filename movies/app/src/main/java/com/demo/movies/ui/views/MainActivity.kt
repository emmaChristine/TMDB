package com.demo.movies.ui.views

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.demo.movies.R

internal class MainActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout

    // region Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.fragment_main)
    }

    // endregion Activity
}
