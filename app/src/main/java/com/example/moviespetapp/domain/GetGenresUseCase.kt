package com.example.moviespetapp.domain

import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getGenres(): List<Genre> {
        return repository.getGenres()
    }

}