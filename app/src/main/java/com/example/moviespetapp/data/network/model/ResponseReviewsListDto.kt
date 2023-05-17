package com.example.moviespetapp.data.network.model

import com.example.moviespetapp.domain.Review
import com.google.gson.annotations.SerializedName

data class ResponseReviewsListDto(

    @SerializedName("docs")
    private var reviews: List<Review>? = null

)
