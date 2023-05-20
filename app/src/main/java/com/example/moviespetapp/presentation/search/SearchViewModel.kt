package com.example.moviespetapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.GetMoviesForGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getMoviesForGenreUseCase: GetMoviesForGenreUseCase

    fun initLD() {
        viewModelScope.launch {
        }
    }

}