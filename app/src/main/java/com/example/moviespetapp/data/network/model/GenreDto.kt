package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("slug")
    val slug: String

) {

}
