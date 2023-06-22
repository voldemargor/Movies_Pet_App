package com.example.moviespetapp.presentation.mainscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.entity.Genre
import com.example.moviespetapp.domain.usecase.GetGenresUseCase
import com.example.moviespetapp.domain.usecase.GetMainScreenComedyMoviesUseCase
import com.example.moviespetapp.domain.usecase.GetMainScreenFictionMoviesUseCase
import com.example.moviespetapp.domain.usecase.GetMainScreenHorrorMoviesUseCase
import com.example.moviespetapp.domain.usecase.GetMainScreenKidMoviesUseCase
import com.example.moviespetapp.domain.usecase.GetMainScreenNewMoviesUseCase
import com.example.moviespetapp.domain.usecase.GetMainScreenPopularMoviesUseCase
import com.example.moviespetapp.domain.usecase.GetMainScreenSoonMoviesUseCase
import com.example.moviespetapp.domain.usecase.GetMoviesByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var getGenresUseCase: GetGenresUseCase
    @Inject lateinit var getMoviesByGenreUseCase: GetMoviesByGenreUseCase
    @Inject lateinit var getMainScreenNewMoviesUseCase: GetMainScreenNewMoviesUseCase
    @Inject lateinit var getMainScreenSoonMoviesUseCase: GetMainScreenSoonMoviesUseCase
    @Inject lateinit var getMainScreenPopularMoviesUseCase: GetMainScreenPopularMoviesUseCase
    @Inject lateinit var getMainScreenFictionMoviesUseCase: GetMainScreenFictionMoviesUseCase
    @Inject lateinit var getMainScreenComedyMoviesUseCase: GetMainScreenComedyMoviesUseCase
    @Inject lateinit var getMainScreenHorrorMoviesUseCase: GetMainScreenHorrorMoviesUseCase
    @Inject lateinit var getMainScreenKidMoviesUseCase: GetMainScreenKidMoviesUseCase

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    private val _newMovies = MutableLiveData<DataLoadingResult>()
    val newMovies: LiveData<DataLoadingResult> get() = _newMovies

    private val _soonMovies = MutableLiveData<DataLoadingResult>()
    val soonMovies: LiveData<DataLoadingResult> get() = _soonMovies

    private val _popularMovies = MutableLiveData<DataLoadingResult>()
    val popularMovies: LiveData<DataLoadingResult> get() = _popularMovies

    private val _fictionMovies = MutableLiveData<DataLoadingResult>()
    val fictionMovies: LiveData<DataLoadingResult> get() = _fictionMovies

    private val _comedyMovies = MutableLiveData<DataLoadingResult>()
    val comedyMovies: LiveData<DataLoadingResult> get() = _comedyMovies

    private val _horrorMovies = MutableLiveData<DataLoadingResult>()
    val horrorMovies: LiveData<DataLoadingResult> get() = _horrorMovies

    private val _kidMovies = MutableLiveData<DataLoadingResult>()
    val kidMovies: LiveData<DataLoadingResult> get() = _kidMovies

    fun initMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("MainFragmentViewModel", "start loading")
            _newMovies.postValue(getMainScreenNewMoviesUseCase.getMovies())
            _soonMovies.postValue(getMainScreenSoonMoviesUseCase.getMovies())
            _popularMovies.postValue(getMainScreenPopularMoviesUseCase.getMovies())
            _fictionMovies.postValue(getMainScreenFictionMoviesUseCase.getMovies())
            _comedyMovies.postValue(getMainScreenComedyMoviesUseCase.getMovies())
            _horrorMovies.postValue(getMainScreenHorrorMoviesUseCase.getMovies())
            _kidMovies.postValue(getMainScreenKidMoviesUseCase.getMovies())
            Log.d("MainFragmentViewModel", "finish loading")
        }
    }

    fun initGenres() {
        viewModelScope.launch {
            val genres = withContext(Dispatchers.IO) { getGenresUseCase.getGenres() }
            _genres.value = genres
        }
    }


}