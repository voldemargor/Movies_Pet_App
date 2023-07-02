package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ResponseListMovieDto(

    @SerializedName("docs")
    val movies: List<MovieDto>

)
