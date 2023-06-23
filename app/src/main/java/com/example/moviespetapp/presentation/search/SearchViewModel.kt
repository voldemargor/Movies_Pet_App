package com.example.moviespetapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.App
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.usecase.GetMoviesBySearchUseCase
import com.example.moviespetapp.presentation.Canceled
import com.example.moviespetapp.presentation.Loading
import com.example.moviespetapp.presentation.Error
import com.example.moviespetapp.presentation.Result
import com.example.moviespetapp.presentation.JobStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getMoviesBySearchUseCase: GetMoviesBySearchUseCase

    @Inject lateinit var context: App

    private val _jobStatus = MutableLiveData<JobStatus>()
    val jobStatus: LiveData<JobStatus> get() = _jobStatus

    private val _showEmptyState = MutableLiveData<Any>()
    val showEmptyState: LiveData<Any> get() = _showEmptyState

    private var allMovies = mutableListOf<Movie>()

    private var prevRequest: String? = null
    private var jobLoading: Job = viewModelScope.launch {}

    init {
        _showEmptyState.value = Any()
    }

    fun loadMovies(searchInput: String) {

        val request = searchInput.trim()
        if (request.equals(prevRequest)) return
        prevRequest = request

        allMovies.clear()
        jobLoading.cancel()
        _jobStatus.value = Canceled

        jobLoading = viewModelScope.launch(Dispatchers.IO) {
            delay(100)
            withContext(Dispatchers.Main) { _jobStatus.value = Loading }
            val loadingResult = getMoviesBySearchUseCase.getMovies(request, 1)
            ensureActive()
            withContext(Dispatchers.Main) { extractData(loadingResult) }
        }
    }

    private fun extractData(loadingResult: DataLoadingResult) {
        when (loadingResult) {
            is DataLoadingResult.Success<*> -> {
                allMovies.addAll(loadingResult.data as List<Movie>)
                _jobStatus.value = Result(allMovies.toList())
            }

            is DataLoadingResult.Failed ->
                _jobStatus.value = Error(loadingResult.exception.message)
        }
    }

}