package com.example.moviespetapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Votes(

    val kp: String,
    val imdb: String

) : Parcelable
