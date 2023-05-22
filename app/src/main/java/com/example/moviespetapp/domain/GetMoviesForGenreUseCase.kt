package com.example.moviespetapp.domain

import javax.inject.Inject

class GetMoviesForGenreUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(genreName: String): List<Movie> {
        return repository.getMoviesForGenre(genreName)
    }

}