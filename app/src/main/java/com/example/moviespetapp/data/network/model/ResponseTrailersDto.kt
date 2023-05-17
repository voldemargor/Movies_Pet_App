package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ResponseTrailersDto(

    @SerializedName("videos")
    private var trailersList: TrailersListDto? = null

)
