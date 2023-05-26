package com.example.moviespetapp.data.network.model

import com.example.moviespetapp.domain.Poster
import com.example.moviespetapp.domain.Rating
import com.google.gson.annotations.SerializedName

data class MovieInfoDto(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("year")
    val year: Int = 0,

    @SerializedName("poster")
    val poster: Poster? = null,

    @SerializedName("rating")
    val rating: Rating? = null,

    @SerializedName("videos")
    val videos: TrailersListDto? = null,

    )