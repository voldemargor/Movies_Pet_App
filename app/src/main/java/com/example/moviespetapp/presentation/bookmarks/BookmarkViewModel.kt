package com.example.moviespetapp.presentation.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.usecase.GetFavMoviesUseCase
import com.example.moviespetapp.domain.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel  @Inject constructor() : ViewModel() {

    @Inject lateinit var getFavMoviesUseCase: GetFavMoviesUseCase

    lateinit var movies: LiveData<List<Movie>>

    fun initFavMoviesLD() {
        viewModelScope.launch {
            movies = getFavMoviesUseCase.getFavMovies()
        }
    }

}