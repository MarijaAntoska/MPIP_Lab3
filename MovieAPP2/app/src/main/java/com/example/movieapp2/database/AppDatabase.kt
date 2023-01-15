package com.example.movieapp2.database

import android.content.Context
import android.media.Rating
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp2.database.dao.MoviesDao
import com.example.movieapp2.models.Movies
import com.example.movieapp2.models.Rate


@Database(entities = [Movies::class, Rate::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): MoviesDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = createInstance(context)
            }
            return instance!!
        }

        private fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "movies.db"
            ).build()
        }
    }
}