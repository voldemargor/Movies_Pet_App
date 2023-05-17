package com.example.moviespetapp.domain

import androidx.lifecycle.LiveData

interface Repository {

    suspend fun getMoviesForGenre(genreName: String): List<Movie>

    fun getFavMovies(): LiveData<List<Movie>>

    suspend fun getMovie(id: Int): Movie

    suspend fun addToFavorites(movie: Movie): Long

    suspend fun deleteFromFavorites(movie: Movie)

    suspend fun getGenres(): List<Genre>


}