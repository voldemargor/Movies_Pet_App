package com.example.moviespetapp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: Repository) {

    fun getMovies(): LiveData<List<Movie>> {
        return repository.getMovies()
    }

}