package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieSearchResultDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("year")
    val year: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("alternativeName")
    val alternativeName: String?,

    @SerializedName("poster")
    val poster: String?,

    @SerializedName("rating")
    val rating: Double?,

    )