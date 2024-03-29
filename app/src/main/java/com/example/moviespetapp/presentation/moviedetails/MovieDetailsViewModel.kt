package com.example.moviespetapp.presentation.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.data.sharedprefs.BookmarkService
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.usecase.AddBookmarkUseCase
import com.example.moviespetapp.domain.usecase.GetMovieDetailsUseCase
import com.example.moviespetapp.domain.usecase.RemoveBookmarkUseCase
import com.example.moviespetapp.presentation.ExceptionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor() : ExceptionViewModel() {

    @Inject lateinit var bookmarkService: BookmarkService
    @Inject lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    @Inject lateinit var addBookmarkUseCase: AddBookmarkUseCase
    @Inject lateinit var removeBookmarkUseCase: RemoveBookmarkUseCase

    private lateinit var movie: Movie

    private val _currentMovie = MutableLiveData<Movie>()
    val currentMovie: LiveData<Movie> get() = _currentMovie

    private val _isBookmark = MutableLiveData<Boolean>()
    val isBookmark: LiveData<Boolean> get() = _isBookmark

    private val _displayLoader = MutableLiveData<Boolean>()
    val displayLoader: LiveData<Boolean> get() = _displayLoader

    fun loadMovieData(movieId: Int?) {
        movieId ?: throw RuntimeException("movieId is Null")
        _displayLoader.value = true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val movie = getMovieDetailsUseCase.getMovie(movieId)
            withContext(Dispatchers.Main) {
                this@MovieDetailsViewModel.movie = movie
                _currentMovie.value = movie
                _isBookmark.value = bookmarkService.hasBookmark(movie.id)
                _displayLoader.value = false
            }
        }
    }

    fun handleBookmarkAction() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (bookmarkService.hasBookmark(movie.id))
                    removeBookmarkUseCase.remove(movie)
                else addBookmarkUseCase.add(movie)
            }
            _isBookmark.value = bookmarkService.hasBookmark(movie.id)
        }
    }

}