package com.demo.movies.util

import android.app.Application

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 *
 */
class TestApp : Application()