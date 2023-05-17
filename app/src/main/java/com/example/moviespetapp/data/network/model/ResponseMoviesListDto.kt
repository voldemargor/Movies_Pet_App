package com.example.moviespetapp.data.network.model

import com.example.moviespetapp.domain.Movie
import com.google.gson.annotations.SerializedName

data class ResponseMoviesListDto(

    @SerializedName("docs")
    val movies: List<Movie>

)
