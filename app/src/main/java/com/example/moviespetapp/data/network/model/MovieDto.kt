package com.example.moviespetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(

    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("alternativeName")
    val alternativeName: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("shortDescription")
    val shortDescription: String? = null,

    @SerializedName("year")
    val year: Int = 0,

    @SerializedName("poster")
    val poster: PosterDto? = null,

    @SerializedName("rating")
    val rating: RatingDto,

    @SerializedName("videos")
    val videos: TrailersListDto? = null,

    @SerializedName("genres")
    val genres: List<GenreDto>? = null,

    @SerializedName("votes")
    val votes: VotesDto,

    @SerializedName("countries")
    val countries: List<CountryDto>?,

    @SerializedName("movieLength")
    val movieLength: Int?,

    @SerializedName("ageRating")
    val ageRating: Int?,

    @SerializedName("similarMovies")
    val similarMovies: List<MovieSimilarDto>?,

    )