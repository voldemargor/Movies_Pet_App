package com.example.moviespetapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.GetMoviesForGenreUseCase
import com.example.moviespetapp.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListScreenViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getMoviesForGenreUseCase: GetMoviesForGenreUseCase

    //lateinit var movies: LiveData<List<Movie>>
    var movies = MutableLiveData<List<Movie>>()

    //private val _movies = MutableLiveData<List<Movie>>()
    //val movies: LiveData<List<Movie>> get() = _movies

    fun initMovies(genreName: String) {
        viewModelScope.launch {
            movies.value = getMoviesForGenreUseCase.getMovies(genreName)
        }
    }



}