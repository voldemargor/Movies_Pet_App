package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ResponseListMovieSearchResultDto(

    @SerializedName("docs")
    val movies: List<MovieSearchResultDto>

)
