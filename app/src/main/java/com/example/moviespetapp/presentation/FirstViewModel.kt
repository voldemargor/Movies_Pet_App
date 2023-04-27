package com.example.moviespetapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.GetMoviesUseCase
import com.example.moviespetapp.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getMoviesUseCase: GetMoviesUseCase

    lateinit var movies: LiveData<List<Movie>>

    fun initMoviesLD() {
        viewModelScope.launch {
            Log.d("mylog", "initMoviesLD")
            Log.d("mylog", "MainViewModel getMoviesUseCase: $getMoviesUseCase")
            movies = getMoviesUseCase.getMovies()
        }
    }


}