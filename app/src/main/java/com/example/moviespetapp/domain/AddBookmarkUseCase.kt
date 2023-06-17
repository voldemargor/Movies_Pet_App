package com.example.moviespetapp.domain

import javax.inject.Inject

class AddBookmarkUseCase @Inject constructor(private val repository: Repository) {

    suspend fun add(movie: Movie) {
        repository.addBookmark(movie)
    }

}