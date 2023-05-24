package com.example.moviespetapp.domain

import java.lang.Exception
import javax.inject.Inject

class GetMoviesForGenreUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(genreName: String): Pair<List<Movie>, Exception?> {
        return repository.getMoviesForGenre(genreName)
    }

}