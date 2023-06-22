package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieSimilarDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("poster")
    val poster: PosterDto?,

    )