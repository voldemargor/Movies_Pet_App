package com.example.moviespetapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    val year: Int,
    val name: String?,
    val description: String?,
    val poster: Poster?,
    val rating: Rating?,
    val id: Int = UNDEFINED_ID,
    val trailers: List<Trailer>?

) : Parcelable {

    companion object {
        const val UNDEFINED_ID = 0
    }

}
