package com.example.moviespetapp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetFavMoviesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getFavMovies(): LiveData<List<Movie>> {
        return repository.getBookmarkMovies()
    }

}