package com.example.moviespetapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    val year: Int,
    val name: String?,
    val alternativeName: String?,
    val description: String?,
    val shortDescription: String?,
    val poster: Poster?,
    val rating: Rating?,
    val id: Int = UNDEFINED_ID,
    val trailers: List<Trailer>?,
    val genres: List<Genre>?,
    val votes: Votes,
    val country: Country,
    val movieLength: Int?,
    val ageRating: Int?,
    val similarMovies: List<MovieShort>?,

    ) : Parcelable {

    companion object {
        const val UNDEFINED_ID = 0
    }

}
