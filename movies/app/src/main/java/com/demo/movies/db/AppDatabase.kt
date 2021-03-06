package com.demo.movies.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.movies.data.dto.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE
            ?: synchronized(LOCK) {
            INSTANCE
                ?: buildDatabase(context).also { INSTANCE = it }
        }

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "movies.db")
                .build()


    }
}
