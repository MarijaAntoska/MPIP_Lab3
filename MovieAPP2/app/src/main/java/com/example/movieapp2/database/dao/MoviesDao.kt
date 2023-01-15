package com.example.movieapp2.database.dao

import androidx.room.*
import com.example.movieapp2.database.relationship.MovieWithRatings
import com.example.movieapp2.models.Movies
import com.example.movieapp2.models.Rate
import java.lang.Exception

@Dao
abstract class MoviesDao {

    @Transaction
    @Query("SELECT * FROM Movies WHERE imdbID = :id")
    abstract fun getMoviesWithRatings(id: String) : MovieWithRatings?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(Movies: Movies)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRatings(rate: MutableList<Rate>)


    fun insertMoviesWithRatings(Movies: Movies, rates: MutableList<Rate>)
    {
        try{
            insertMovies(Movies)
            for(rate in rates)
            {
                rate.imdbID = Movies.imdbID
            }
            insertRatings(rates)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
    }
}