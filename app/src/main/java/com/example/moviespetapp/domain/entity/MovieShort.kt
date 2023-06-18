package com.example.moviespetapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieShort(

    val id: Int,
    val name: String,
    val poster: Poster?,

    ) : Parcelable {

}
