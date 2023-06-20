package com.example.moviespetapp.domain.usecase

import com.example.moviespetapp.domain.Repository
import com.example.moviespetapp.domain.entity.Movie
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovie(id : Int) : Movie {
        return repository.getMovieDetails(id)
    }

}