package com.demo.movies

import androidx.test.rule.ActivityTestRule
import com.demo.movies.framework.AcceptanceTest
import com.demo.movies.ui.views.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Simple UI test.
 */

internal class BasicUiTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @get:Rule
    private var activityScenarioRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {}

    @After
    fun tearDown() {}

    @Test
    fun activityLaunches() {
        checkThat.viewIsVisible(R.id.movies_list)
    }
}