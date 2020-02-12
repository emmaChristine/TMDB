package com.demo.movies.util

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.demo.movies.TestApp

/**
 * A custom [AndroidJUnitRunner] used to replace the application used in tests with a
 * [MyTestApplication].
 */
class MyCustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, TestApp::class.java.name, context)
    }
}