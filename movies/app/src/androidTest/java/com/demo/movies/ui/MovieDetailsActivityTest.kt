package com.demo.movies.ui

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.demo.movies.R
import com.demo.movies.ui.views.MoviesActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MovieDetailsActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MoviesActivity::class.java)

    @Test
    fun movieDetailsMarkAsFavouriteTest() {

        // wait for all animations to be completed
        Thread.sleep(2000)

        val cardView = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.movies_list),
                        childAtPosition(
                            withId(R.id.fragment_movies),
                            2
                        )
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        cardView.perform(click())

        val floatingActionButton = onView(
            allOf(
                withId(R.id.fab_save_movie),
                childAtPosition(
                    allOf(
                        withId(R.id.fragment_preference),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
