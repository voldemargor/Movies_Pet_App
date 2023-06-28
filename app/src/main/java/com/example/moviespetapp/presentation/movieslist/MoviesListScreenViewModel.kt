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
import com.example.moviespetapp.presentation.Error
import com.example.moviespetapp.presentation.JobStatus
import com.example.moviespetapp.presentation.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesListScreenViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getMoviesByGenreUseCase: GetMoviesByGenreUseCase

    private val _JobStatus = MutableLiveData<JobStatus>()
    val jobStatus: LiveData<JobStatus> get() = _JobStatus

    // Pagination
    private var queryPage = 1
    private var allMovies = mutableListOf<Movie>()

    fun loadMovies(genreName: String) {
        // Если загрузка уже идет, то стартовать новую не нужно
        if (jobStatus.value is Loading) return

        _JobStatus.value = Loading

        viewModelScope.launch(Dispatchers.IO) {
            val loadingResult =
                getMoviesByGenreUseCase.getMovies(genreName, queryPage)
            withContext(Dispatchers.Main) { extractData(loadingResult) }
        }
    }

    private fun extractData(loadingResult: DataLoadingResult) {
        when (loadingResult) {
            is Success<*> -> {
                queryPage++
                allMovies.addAll(loadingResult.data as List<Movie>)
                _JobStatus.value = Result(allMovies.toList())
            }

            is Failed ->
                _JobStatus.value = Error(loadingResult.exception.message)
        }
    }

}