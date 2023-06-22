package com.example.moviespetapp.presentation.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.data.sharedprefs.BookmarkService
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.DataLoadingResult.Failed
import com.example.moviespetapp.domain.DataLoadingResult.Success
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.usecase.GetBookedMoviesUseCase
import com.example.moviespetapp.presentation.Loading
import com.example.moviespetapp.presentation.Error
import com.example.moviespetapp.presentation.JobStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getBookedMoviesUseCase: GetBookedMoviesUseCase
    @Inject lateinit var bookmarkService: BookmarkService

    private val _JobStatus = MutableLiveData<JobStatus>()
    val jobStatus: LiveData<JobStatus> get() = _JobStatus

    // Pagination
    private var apiPage = 1
    private var allMovies = mutableListOf<Movie>()

    private val _showEmptyState = MutableLiveData<Any>()
    val showEmptyState: LiveData<Any> get() = _showEmptyState

    fun loadMovies() {
        // Проверка на Empty State
        if (bookmarkService.bookedIDs.isEmpty()) {
            _showEmptyState.value = Any()
            return
        }

        // Не дергать API, если все уже загружено
        if (allMovies.size >= bookmarkService.bookedIDs.size) return

        // Если загрузка уже идет, то стартовать новую не нужно
        if (jobStatus.value is Loading) return

        _JobStatus.value = Loading

        viewModelScope.launch(Dispatchers.IO) {
            val loadingResult =
                getBookedMoviesUseCase.getMovies(bookmarkService.bookedIDs, apiPage)
            withContext(Dispatchers.Main) { extractData(loadingResult) }
        }
    }

    private fun extractData(loadingResult: DataLoadingResult) {
        when (loadingResult) {
            is Success<*> -> {
                apiPage++
                allMovies.addAll(loadingResult.data as List<Movie>)
                _JobStatus.value = com.example.moviespetapp.presentation.Result(allMovies.toList())
            }

            is Failed ->
                _JobStatus.value = Error(loadingResult.exception.message)
        }
    }

}