package com.demo.movies

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.demo.movies.ui.views.MainActivity
import org.junit.Rule
import org.junit.Test

class MoviesActivityTest {

    @Test
    fun clickMovie_OpensMovieDetails() {

        Espresso.onView(ViewMatchers.withId(R.id.movie_image)).perform(ViewActions.click())

        // Then the Movie Details should open
        Espresso.onView(ViewMatchers.withId(R.id.detail_image))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @get:Rule
    private var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

}