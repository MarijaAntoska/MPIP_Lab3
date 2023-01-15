package com.example.movieapp2.database.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.example.movieapp2.models.Movies
import com.example.movieapp2.models.Rate

data class MovieWithRatings (
    @Embedded
    val Movies: Movies,
    @Relation(
        parentColumn = "imdbID",
        entityColumn = "imdbID"
    )
    val rate: MutableList<Rate>
)