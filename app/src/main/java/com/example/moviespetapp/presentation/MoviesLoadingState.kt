package com.example.moviespetapp.presentation

import com.example.moviespetapp.domain.Movie

sealed class MoviesLoadingState

object MoviesLoading : MoviesLoadingState()
class MoviesLoadingError(val message: String?) : MoviesLoadingState()
class MoviesLoadingResult(val movies: List<Movie>) : MoviesLoadingState()