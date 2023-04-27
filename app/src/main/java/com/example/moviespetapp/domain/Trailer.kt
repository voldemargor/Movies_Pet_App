package com.example.moviespetapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trailer(

    val name: String,
    val url: String

) : Parcelable
