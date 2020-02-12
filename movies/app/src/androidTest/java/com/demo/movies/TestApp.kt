package com.demo.movies

import android.app.Application

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 *
 */
class TestApp : Application()