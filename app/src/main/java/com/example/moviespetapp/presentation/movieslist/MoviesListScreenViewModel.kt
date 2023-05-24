package com.example.moviespetapp.presentation.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.GetMoviesForGenreUseCase
import com.example.moviespetapp.domain.Movie
import com.example.moviespetapp.presentation.MoviesLoading
import com.example.moviespetapp.presentation.MoviesLoadingError
import com.example.moviespetapp.presentation.MoviesLoadingResult
import com.example.moviespetapp.presentation.MoviesLoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
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
        // Если загрузка уже идет, то стартовать новую не нужно
        if (movies.value is MoviesLoading) return
        _movies.value = MoviesLoading

        Log.d("mylog", "MoviesListScreenViewModel: loadMovies()")

        viewModelScope.launch {
            getMoviesForGenreUseCase.getMovies(genreName).apply {
                if (loadingSuccess(this)) {
                    _movies.value = MoviesLoadingResult(first)
                    moviesPage++
                } else
                    _movies.value = MoviesLoadingError(second?.message)
            }
        }
    }

    private fun loadingSuccess(result: Pair<List<Movie>, Exception?>) =
        result.second == null

    //fun loadMovies(genreName: String) {
    //    viewModelScope.launch {
    //        movies.value = getMoviesForGenreUseCase.getMovies(genreName)
    //    }
    //}

}