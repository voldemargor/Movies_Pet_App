package com.example.moviespetapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieShort(

    val id: Int,
    val name: String,
    val poster: Poster?,

    ) : Parcelable {

}
