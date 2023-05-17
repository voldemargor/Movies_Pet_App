package com.example.moviespetapp.data.network.model

import com.example.moviespetapp.domain.Trailer
import com.google.gson.annotations.SerializedName

data class TrailersListDto(

    @SerializedName("trailers")
    private var trailers: List<Trailer?>? = null

)
