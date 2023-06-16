package com.example.moviespetapp.domain

import javax.inject.Inject

class GetMainScreenSoonMoviesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(): DataLoadingResult {
        return repository.getMainScreenSoonMovies()
    }

}