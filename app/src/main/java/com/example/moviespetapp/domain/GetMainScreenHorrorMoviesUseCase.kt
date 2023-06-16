package com.example.moviespetapp.domain

import javax.inject.Inject

class GetMainScreenHorrorMoviesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(): DataLoadingResult {
        return repository.getMainScreenHorrorMovies()
    }

}