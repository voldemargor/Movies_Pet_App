package com.example.moviespetapp.presentation.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.GetMoviesForGenreUseCase
import com.example.moviespetapp.presentation.MoviesLoading
import com.example.moviespetapp.presentation.MoviesLoadingError
import com.example.moviespetapp.presentation.MoviesLoadingResult
import com.example.moviespetapp.presentation.MoviesLoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListScreenViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getMoviesForGenreUseCase: GetMoviesForGenreUseCase

    //lateinit var movies: LiveData<List<Movie>>
    //var movies = MutableLiveData<List<Movie>>()

    private val _movies = MutableLiveData<MoviesLoadingState>()
    val movies: LiveData<MoviesLoadingState> get() = _movies

    var moviesPage = 1

    //private val _movies = MutableLiveData<List<Movie>>()
    //val movies: LiveData<List<Movie>> get() = _movies

    fun loadMovies(genreName: String) {
        _movies.value = MoviesLoading
        viewModelScope.launch {
            _movies.value = MoviesLoadingResult(getMoviesForGenreUseCase.getMovies(genreName))
            if (_movies.value == null)
                _movies.value = MoviesLoadingError
        }
    }

    //fun loadMovies(genreName: String) {
    //    viewModelScope.launch {
    //        movies.value = getMoviesForGenreUseCase.getMovies(genreName)
    //    }
    //}

}