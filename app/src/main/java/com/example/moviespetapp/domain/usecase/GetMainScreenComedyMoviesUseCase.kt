package com.example.moviespetapp.domain.usecase

import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.Repository
import javax.inject.Inject

class GetMainScreenComedyMoviesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(): DataLoadingResult {
        return repository.getMainScreenComedyMovies()
    }

}