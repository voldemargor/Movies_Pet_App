package com.example.moviespetapp.domain.usecase

import com.example.moviespetapp.domain.Repository
import com.example.moviespetapp.domain.entity.Genre
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getGenres(): List<Genre> {
        return repository.getGenres()
    }

}