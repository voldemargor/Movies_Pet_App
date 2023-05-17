package com.example.moviespetapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.Genre
import com.example.moviespetapp.domain.GetGenresUseCase
import com.example.moviespetapp.domain.GetMoviesForGenreUseCase
import com.example.moviespetapp.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor() : ViewModel() {

    //@Inject lateinit var getMoviesForGenreUseCase: GetMoviesForGenreUseCase
    //@Inject lateinit var getMovieUseCase: GetMovieUseCase
    @Inject lateinit var getGenresUseCase: GetGenresUseCase

    lateinit var movies: LiveData<List<Movie>>

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    fun initMoviesLD() {
        viewModelScope.launch {
            //Log.d("mylog", "initMoviesLD")
            //Log.d("mylog", "MainViewModel getMoviesUseCase: $getMoviesForGenreUseCase")
        }
    }

    fun initGenres() {
        viewModelScope.launch {
            _genres.value = getGenresUseCase.getGenres()
        }
    }


}