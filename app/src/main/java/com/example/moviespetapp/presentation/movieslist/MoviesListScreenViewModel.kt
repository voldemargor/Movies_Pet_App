package com.example.moviespetapp.presentation.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.DataLoadingResult.Failed
import com.example.moviespetapp.domain.DataLoadingResult.Success
import com.example.moviespetapp.domain.usecase.GetMoviesByGenreUseCase
import com.example.moviespetapp.domain.entity.Movie
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
    private var apiPage = 1
    private var allMovies = mutableListOf<Movie>()

    fun loadMovies(genreName: String) {
        // Если загрузка уже идет, то стартовать новую не нужно
        if (moviesLoadingState.value is Loading) return

        _moviesLoadingState.value = Loading

        viewModelScope.launch {
            getData(getMoviesByGenreUseCase.getMovies(genreName, apiPage))
        }
    }

    private fun getData(loadingResult: DataLoadingResult) {
        when (loadingResult) {
            is Success<*> -> {
                apiPage++
                allMovies.addAll(loadingResult.data as List<Movie>)
                _moviesLoadingState.value = LoadingSuccess(allMovies.toList())
            }

            is Failed ->
                _moviesLoadingState.value = LoadingError(loadingResult.exception.message)
        }
    }

}