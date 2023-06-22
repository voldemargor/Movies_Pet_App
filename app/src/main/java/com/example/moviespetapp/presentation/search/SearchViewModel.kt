package com.example.moviespetapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.App
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.usecase.GetMoviesBySearchUseCase
import com.example.moviespetapp.presentation.Loading
import com.example.moviespetapp.presentation.Error
import com.example.moviespetapp.presentation.Result
import com.example.moviespetapp.presentation.JobStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getMoviesBySearchUseCase: GetMoviesBySearchUseCase

    @Inject lateinit var context: App

    private val _jobStatus = MutableLiveData<JobStatus>()
    val jobStatus: LiveData<JobStatus> get() = _jobStatus

    // Pagination
    private var apiPage = 1
    private var allMovies = mutableListOf<Movie>()

    private var prevRequest: String? = null

    fun loadMovies(searchInput: String) {

        //val char = request.first { it.isLetter() }
        //
        //// Нужно удалять из строки всю ерунду и потом брать буквы
        //// А если букв нет то кидать в хуевый поиск
        //// https://stackoverflow.com/questions/45929687/kotlin-remove-all-non-alphanumeric-characters
        //
        //if (Character.UnicodeBlock.of(char) == Character.UnicodeBlock.BASIC_LATIN) {
        //    // В англ поиск
        //    Toast.makeText(context, "Lang: En", Toast.LENGTH_SHORT).show()
        //} else if (Character.UnicodeBlock.of(char) == Character.UnicodeBlock.CYRILLIC) {
        //    // В русский поиск
        //    Toast.makeText(context, "Lang: Ru", Toast.LENGTH_SHORT).show()
        //} else {
        //    // В хуевый поиск
        //    Toast.makeText(context, "Lang: Undefined", Toast.LENGTH_SHORT).show()
        //}


        //if (request.isEmpty() || request.equals(prevRequest)) return
        //prevRequest = request

        //if (viewModelScope.isActive) {
        //    viewModelScope.cancel()
        //    _moviesLoadingState.value = LoadingCanceled
        //}
        //
        //allMovies.clear()
        //_moviesLoadingState.value = Loading
        //
        //viewModelScope.launch {
        //    getData(getMoviesBySearchUseCase.getMovies(request, 1))
        //}

        //viewModelScope.cancel()

        val job = viewModelScope.launch {
            allMovies.clear()
            val request = searchInput.trim()
            //_moviesLoadingState.value = LoadingCanceled
            //delay(200)
            _jobStatus.value = Loading
            getData(getMoviesBySearchUseCase.getMovies(request, 1))
        }
        job.invokeOnCompletion {

        }
    }

    private fun getData(loadingResult: DataLoadingResult) {
        when (loadingResult) {
            is DataLoadingResult.Success<*> -> {
                apiPage++
                allMovies.addAll(loadingResult.data as List<Movie>)
                _jobStatus.value = Result(allMovies.toList())
            }

            is DataLoadingResult.Failed ->
                _jobStatus.value = Error(loadingResult.exception.message)
        }
    }

    companion object {
        private const val LANG_UNDEFINED = "und"
        private const val LANG_RUSSIAN = "ru"
        private const val LANG_ENGLISH = "en"
    }

}