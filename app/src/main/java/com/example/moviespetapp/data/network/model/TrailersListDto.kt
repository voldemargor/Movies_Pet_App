package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class TrailersListDto(

    @SerializedName("trailers")
    var trailers: List<TrailerDto>? = null

)
