package com.example.moviespetapp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetMoviesForGenreUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(genreName: String): List<Movie> {
        return repository.getMoviesForGenre(genreName)
    }

}