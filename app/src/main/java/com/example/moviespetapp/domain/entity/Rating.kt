package com.example.moviespetapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(

    val kp: Double,
    val imdb: Double,

) : Parcelable
