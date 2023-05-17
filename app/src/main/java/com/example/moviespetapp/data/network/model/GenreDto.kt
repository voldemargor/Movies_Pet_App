package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreDto(

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("slug")
    @Expose
    val slug: String

) {

}
