package com.example.moviespetapp.domain

import javax.inject.Inject

class GetMainScreenPopularMoviesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovies(): DataLoadingResult {
        return repository.getMainScreenPopularMovies()
    }

}