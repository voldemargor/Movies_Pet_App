package com.example.moviespetapp.presentation.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.DataLoadingResult.Failed
import com.example.moviespetapp.domain.DataLoadingResult.Success
import com.example.moviespetapp.domain.GetMoviesByGenreUseCase
import com.example.moviespetapp.domain.Movie
import com.example.moviespetapp.presentation.Loading
import com.example.moviespetapp.presentation.LoadingError
import com.example.moviespetapp.presentation.LoadingSuccess
import com.example.moviespetapp.presentation.MoviesLoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListScreenViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getMoviesByGenreUseCase: GetMoviesByGenreUseCase

    private val _moviesLoadingState = MutableLiveData<MoviesLoadingState>()
    val moviesLoadingState: LiveData<MoviesLoadingState> get() = _moviesLoadingState

    // Pagination
    private var moviesPage = 1
    private var allMovies = mutableListOf<Movie>()

    fun loadMovies(genreName: String) {
        // Если загрузка уже идет, то стартовать новую не нужно
        if (moviesLoadingState.value is Loading) return
        _moviesLoadingState.value = Loading

        Log.d("mylog", "MoviesListScreenViewModel: loadMovies()")

        viewModelScope.launch {
            getData(getMoviesByGenreUseCase.getMovies(genreName, moviesPage))
        }
    }

    private fun getData(loadingResult: DataLoadingResult) {
        when (loadingResult) {
            is Success<*> -> {
                moviesPage++
                allMovies.addAll(loadingResult.data as List<Movie>)
                _moviesLoadingState.value = LoadingSuccess(allMovies.toList())
            }

            is Failed ->
                _moviesLoadingState.value = LoadingError(loadingResult.exception.message)
        }
    }

    //private fun getData(loadingResult: DataLoadingResult) {
    //    when (loadingResult) {
    //        is Success<*> -> {
    //            moviesPage++
    //            if (allMovies == null)
    //                allMovies = (loadingResult.data as List<Movie>).toMutableList()
    //            else
    //                allMovies?.addAll(loadingResult.data as List<Movie>)
    //
    //            _moviesLoadingState.value =
    //                LoadingSuccess(allMovies ?: throw RuntimeException("allMovies is NULL"))
    //        }
    //
    //        is Failed ->
    //            _moviesLoadingState.value = LoadingError(loadingResult.exception.message)
    //    }
    //}

    //private fun getData(result: DataLoadingResult) {
    //    when (result) {
    //        is Success<*> -> {
    //            _movies.value = MoviesLoadingSuccess(result.data as List<Movie>)
    //            moviesPage++
    //        }
    //
    //        is Failed ->
    //            _movies.value = MoviesLoadingError(result.exception.message)
    //    }
    //}

}