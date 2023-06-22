package com.example.moviespetapp.data.network.model

import com.example.moviespetapp.domain.entity.Movie
import com.google.gson.annotations.SerializedName

data class ResponseSearchResultDto(

    @SerializedName("docs")
    val movies: List<MovieSearchResultDto>

)
