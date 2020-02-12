package com.demo.movies.di

import android.content.Context
import androidx.room.Room
import com.demo.movies.AppConstants.API_SERVIVE_AUTH_HEADER
import com.demo.movies.AppConstants.TMDB_BASE_URL
import com.demo.movies.BuildConfig
import com.demo.movies.MoviesApplication
import com.demo.movies.api.MoviesApi
import com.demo.movies.db.AppDatabase
import com.demo.movies.db.MovieDao
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelsModule::class])
class AppModule() {


    @Provides
    @Singleton
    fun provideContext(app: MoviesApplication): Context = app

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {

        //Creating Auth Interceptor to add api_key query in front of all the requests.
        val authInterceptor = Interceptor { chain ->
            val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter(API_SERVIVE_AUTH_HEADER, BuildConfig.MoviesApiKey)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }

        // Log requests with apiKey only on DEBUG mode
        val httpLoggingInterceptor = if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BASIC
        ) else null

        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(httpClient: OkHttpClient): MoviesApi {

        return Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDb(app: MoviesApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "movies.db")
            // At current moment we don't want to provide migrations and specifically want database to be cleared when upgrade the version.
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: AppDatabase): MovieDao {
        return db.movieDao()
    }
}