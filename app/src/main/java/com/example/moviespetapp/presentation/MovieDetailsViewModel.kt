package com.example.moviespetapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.AddToFavoritesUseCase
import com.example.moviespetapp.domain.GetMovieUseCase
import com.example.moviespetapp.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getMovieUseCase: GetMovieUseCase
    @Inject lateinit var addToFavoritesUseCase: AddToFavoritesUseCase

    private val _currentMovie = MutableLiveData<Movie>()
    val currentMovie: LiveData<Movie> get() = _currentMovie

    fun initMovie(movieId: Int?) {
        movieId ?: throw RuntimeException("movieId is Null")
        viewModelScope.launch {
            _currentMovie.value = getMovieUseCase.getMovie(movieId)
        }
    }

    fun addToFavorites(movie: Movie) {
        viewModelScope.launch {
            addToFavoritesUseCase.add(movie)
        }
    }

}