package com.example.moviespetapp.domain.usecase

import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.Repository
import javax.inject.Inject

class GetMoviesBySearchUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(searchRequest: String, page: Int): DataLoadingResult {
        return repository.getMoviesBySearch(searchRequest, page)
    }

}