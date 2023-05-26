package com.example.moviespetapp.presentation

import com.example.moviespetapp.domain.Movie

sealed class MoviesLoadingState

object Loading : MoviesLoadingState()
class LoadingError(val message: String?) : MoviesLoadingState()
class LoadingSuccess(val movies: List<Movie>) : MoviesLoadingState()