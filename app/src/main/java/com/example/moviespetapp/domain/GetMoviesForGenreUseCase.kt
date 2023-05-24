package com.example.moviespetapp.domain

import javax.inject.Inject

class GetMoviesForGenreUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(genreName: String): DataLoadingResult {
        return repository.getMoviesForGenre(genreName)
    }

}