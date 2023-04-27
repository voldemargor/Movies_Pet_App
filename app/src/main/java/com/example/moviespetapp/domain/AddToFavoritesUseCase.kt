package com.example.moviespetapp.domain

import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun add(movie: Movie) {
        repository.addToFavorites(movie)
    }

}