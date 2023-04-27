package com.example.moviespetapp.domain

import androidx.lifecycle.LiveData

interface Repository {

    fun getMovies(): LiveData<List<Movie>>

    fun getFavMovies(): LiveData<List<Movie>>

    suspend fun getMovie(id: Int): Movie

    suspend fun addToFavorites(movie: Movie): Long

    suspend fun deleteFromFavorites(movie: Movie)


}