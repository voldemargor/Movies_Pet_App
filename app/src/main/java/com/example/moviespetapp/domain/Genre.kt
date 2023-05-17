package com.example.moviespetapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(

    val name: String,
    val slug: String

) : Parcelable {

}
