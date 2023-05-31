package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PosterDto(

    @SerializedName("url")
    val url: String,

    ) {

}
