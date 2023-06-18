package com.example.moviespetapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(

    val author: String,
    val title: String,
    val type: String,
    val reviewText: String

) : Parcelable