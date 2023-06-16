package com.example.moviespetapp.domain

import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(genreName: String, page: Int): DataLoadingResult {
        return repository.getMoviesByGenre(genreName, page)
    }

}