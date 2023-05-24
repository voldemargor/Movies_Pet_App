package com.example.moviespetapp.domain

import androidx.lifecycle.LiveData
import com.example.moviespetapp.data.network.model.ResponseMoviesListDto
import retrofit2.Response
import java.lang.Exception

interface Repository {

    suspend fun getMoviesForGenre(genreName: String): Pair<List<Movie>, Exception?>

    fun getFavMovies(): LiveData<List<Movie>>

    suspend fun getMovie(id: Int): Movie

    suspend fun addToFavorites(movie: Movie): Long

    suspend fun deleteFromFavorites(movie: Movie)

    suspend fun getGenres(): List<Genre>


}