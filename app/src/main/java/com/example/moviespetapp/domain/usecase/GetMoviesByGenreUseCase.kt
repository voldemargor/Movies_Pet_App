package com.example.moviespetapp.domain.usecase

import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.Repository
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(genreName: String, page: Int): DataLoadingResult {
        return repository.getMoviesByGenre(genreName, page)
    }

}