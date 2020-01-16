package com.demo.movies.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.demo.movies.data.local.db.AppDatabase
import com.demo.movies.data.remote.NetworkModule
import com.demo.movies.data.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel(application: Application): AndroidViewModel(application) {
    val parentJob = Job()

    val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    val scope = CoroutineScope(coroutineContext)

    val repository: MoviesRepository = MoviesRepository(NetworkModule.moviesAPI,
        AppDatabase.invoke(application.applicationContext).movieDao())

    fun cancelAllRequests() = coroutineContext.cancel()
}