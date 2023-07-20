package com.example.moviespetapp.domain.usecase

import com.example.moviespetapp.domain.Repository
import com.example.moviespetapp.domain.entity.Movie
import javax.inject.Inject

class RemoveBookmarkUseCase @Inject constructor(private val repository: Repository) {

    suspend fun remove(movie: Movie) {
        repository.removeBookmark(movie)
    }

}