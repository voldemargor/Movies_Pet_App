package com.example.moviespetapp.presentation.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.data.sharedprefs.BookmarkService
import com.example.moviespetapp.domain.usecase.AddBookmarkUseCase
import com.example.moviespetapp.domain.usecase.GetMovieDetailsUseCase
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.usecase.RemoveBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var bookmarkService: BookmarkService
    @Inject lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    @Inject lateinit var addBookmarkUseCase: AddBookmarkUseCase
    @Inject lateinit var removeBookmarkUseCase: RemoveBookmarkUseCase
    private lateinit var movie: Movie

    private val _currentMovie = MutableLiveData<Movie>()
    val currentMovie: LiveData<Movie> get() = _currentMovie

    private val _isBookmark = MutableLiveData<Boolean>()
    val isBookmark: LiveData<Boolean> get() = _isBookmark

    fun initMovie(movieId: Int?) {
        movieId ?: throw RuntimeException("movieId is Null")
        viewModelScope.launch {
            movie = getMovieDetailsUseCase.getMovie(movieId)
            _currentMovie.value = movie
            _isBookmark.value = bookmarkService.hasBookmark(movie.id)
        }
    }

    fun handleBookmarkAction() {



        viewModelScope.launch {
            if (bookmarkService.hasBookmark(movie.id))
                removeBookmarkUseCase.remove(movie)
            else addBookmarkUseCase.add(movie)

            _isBookmark.value = bookmarkService.hasBookmark(movie.id)
        }
    }

}