package com.example.moviespetapp.data.network.model

import com.example.moviespetapp.domain.Poster
import com.example.moviespetapp.domain.Rating
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieInfoDto(

    @SerializedName("id")
    @Expose
    val id: Int = 0,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("year")
    @Expose
    val year: Int = 0,

    @SerializedName("poster")
    @Expose
    val poster: Poster? = null,

    @SerializedName("rating")
    @Expose
    val rating: Rating? = null

)