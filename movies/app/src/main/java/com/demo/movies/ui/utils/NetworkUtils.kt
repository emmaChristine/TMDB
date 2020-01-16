package com.demo.movies.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun isNetworkConnected(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (networkInfo != null)
        return networkInfo?.isConnectedOrConnecting
    else
        return false
}