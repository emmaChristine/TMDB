package com.demo.movies

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.demo.movies.ui.views.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Simple UI test.
 */
@RunWith(AndroidJUnit4::class)
class BasicUiTest {

    @get:Rule
    private var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {}

    @After
    fun tearDown() {}

    @Test
    fun activityLaunches() {
        onView(withId(R.id.movies_list)).check(matches(isDisplayed()))
    }
}