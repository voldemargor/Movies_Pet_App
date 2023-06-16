package com.example.moviespetapp.domain

import javax.inject.Inject

class GetMainScreenFictionMoviesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(): DataLoadingResult {
        return repository.getMainScreenFictionMovies()
    }

}