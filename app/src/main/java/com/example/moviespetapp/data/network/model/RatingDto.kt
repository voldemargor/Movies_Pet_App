package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RatingDto(

    @SerializedName("kp")
    val kp: Double,

    @SerializedName("imdb")
    val imdb: Double,

    ) {

}
