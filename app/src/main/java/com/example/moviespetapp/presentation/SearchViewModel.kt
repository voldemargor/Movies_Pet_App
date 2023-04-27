package com.example.moviespetapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.GetMoviesUseCase
import com.example.moviespetapp.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getMoviesUseCase: GetMoviesUseCase

    lateinit var movies: LiveData<List<Movie>>

    fun initLD() {
        viewModelScope.launch {
        }
    }

}