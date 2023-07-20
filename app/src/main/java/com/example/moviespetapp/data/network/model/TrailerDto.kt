package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class TrailerDto(

    @SerializedName("url")
    val url: String,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("site")
    val site: String? = null,

    @SerializedName("type")
    val type: String? = null,

    )