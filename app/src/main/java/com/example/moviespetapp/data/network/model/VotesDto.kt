package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class VotesDto(

    @SerializedName("kp")
    val kp: String,

    @SerializedName("imdb")
    val imdb: String

)
