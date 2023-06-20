package com.example.moviespetapp.domain.usecase

import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.Repository
import javax.inject.Inject

class GetBookedMoviesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(ids: Array<String>, page: Int): DataLoadingResult {
        return repository.getBookedMovies(ids, page)
    }

}