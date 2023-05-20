package com.example.moviespetapp.presentation

import com.example.moviespetapp.R
import com.example.moviespetapp.domain.Movie
import kotlin.math.round

object Utils {

    fun getRatingFromEntity(movie: Movie): String {
        val rating = movie.rating?.kp ?: 0.0
        val rounded = round(rating * 10) / 10
        return rounded.toString()
    }

    fun getRatingBgColor(movie: Movie): Int {
        val rating = getRatingFromEntity(movie).toDouble()
        if (rating >= 7)
            return R.color.rating_positive
        return R.color.rating_neutral
    }

}