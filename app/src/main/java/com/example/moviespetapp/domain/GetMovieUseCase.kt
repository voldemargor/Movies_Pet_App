package com.example.moviespetapp.domain

import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: Repository) {

    suspend fun getMovie(id : Int) : Movie {
        return repository.getMovie(id)
    }

}