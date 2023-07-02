package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ResponseListTrailerDto(

    @SerializedName("trailers")
    var trailers: List<TrailerDto>? = null

)
