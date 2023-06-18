package com.example.moviespetapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.moviespetapp.domain.Repository
import com.example.moviespetapp.domain.entity.Movie
import javax.inject.Inject

class GetFavMoviesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getFavMovies(): LiveData<List<Movie>> {
        return repository.getBookmarkMovies()
    }

}