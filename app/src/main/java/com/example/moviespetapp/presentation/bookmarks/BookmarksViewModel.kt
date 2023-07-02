package com.example.moviespetapp.presentation.bookmarks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.data.sharedprefs.BookmarkService
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.DataLoadingResult.Failed
import com.example.moviespetapp.domain.DataLoadingResult.Success
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.usecase.GetBookedMoviesUseCase
import com.example.moviespetapp.presentation.Loading
import com.example.moviespetapp.presentation.Error
import com.example.moviespetapp.presentation.ExceptionViewModel
import com.example.moviespetapp.presentation.Result
import com.example.moviespetapp.presentation.JobStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor() : ExceptionViewModel() {

    @Inject lateinit var getBookedMoviesUseCase: GetBookedMoviesUseCase
    @Inject lateinit var bookmarkService: BookmarkService

    private val _jobStatus = MutableLiveData<JobStatus>()
    val jobStatus: LiveData<JobStatus> get() = _jobStatus

    // Pagination
    private var queryPage = 1
    private var allMovies = mutableListOf<Movie>()

    private val _showEmptyState = MutableLiveData<Any>()
    val showEmptyState: LiveData<Any> get() = _showEmptyState

    fun loadMovies() {
        // Проверка на Empty State
        if (bookmarkService.bookedIDs.isEmpty()) {
            _showEmptyState.value = Any()
            return
        }

        Log.d("mylog", "BookmarksViewModel: loadMovies() 1")

        // Не дергать API, если все уже загружено
        if (allMovies.size == bookmarkService.bookedIDs.size) return

        // Если загрузка уже идет, то стартовать новую не нужно
        if (jobStatus.value is Loading) return

        _jobStatus.value = Loading

        Log.d("mylog", "BookmarksViewModel: loadMovies() 2")

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val loadingResult =
                getBookedMoviesUseCase.getMovies(bookmarkService.bookedIDs, queryPage)
            withContext(Dispatchers.Main) { extractData(loadingResult) }
        }
    }

    private fun extractData(loadingResult: DataLoadingResult) {
        when (loadingResult) {
            is Success<*> -> {
                Log.d("mylog", "BookmarksViewModel: loadMovies() 3")
                Log.d("mylog", "BookmarksViewModel: queryPage: $queryPage")
                queryPage++
                allMovies.addAll(loadingResult.data as List<Movie>)
                _jobStatus.value = Result(allMovies.toList())
            }

            is Failed ->
                _jobStatus.value = Error(loadingResult.exception.message)
        }
    }

    fun reset() {
        queryPage = 1
        allMovies.clear()
    }

}