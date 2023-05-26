package com.example.moviespetapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trailer(

    val url: String,
    val name: String?,
    val site: String?,
    val type: String?,

) : Parcelable
