package com.example.moviespetapp.presentation.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.DataLoadingResult.Failed
import com.example.moviespetapp.domain.DataLoadingResult.Success
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

    private val _movies = MutableLiveData<MoviesLoadingState>()
    val movies: LiveData<MoviesLoadingState> get() = _movies

    var moviesPage = 1

    fun loadMovies(genreName: String) {
        // Если загрузка уже идет, то стартовать новую не нужно
        if (movies.value is MoviesLoading) return
        _movies.value = MoviesLoading

        Log.d("mylog", "MoviesListScreenViewModel: loadMovies()")

        viewModelScope.launch {
            getData(getMoviesForGenreUseCase.getMovies(genreName))
        }
    }

    private fun getData(result: DataLoadingResult) {
        when (result) {
            is Success<*> -> {
                _movies.value = MoviesLoadingResult(result.data as List<Movie>)
                moviesPage++
            }

            is Failed ->
                _movies.value = MoviesLoadingError(result.exception.message)
        }
    }

}