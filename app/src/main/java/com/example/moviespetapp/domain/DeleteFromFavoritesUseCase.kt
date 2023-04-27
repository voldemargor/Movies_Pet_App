package com.example.moviespetapp.domain

import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun deleteFromFavorites(movie: Movie) {
        repository.deleteFromFavorites(movie)
    }

}