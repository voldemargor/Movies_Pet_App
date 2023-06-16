package com.example.moviespetapp.presentation.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.Genre
import com.example.moviespetapp.domain.GetGenresUseCase
import com.example.moviespetapp.domain.GetMainScreenComedyMoviesUseCase
import com.example.moviespetapp.domain.GetMainScreenFictionMoviesUseCase
import com.example.moviespetapp.domain.GetMainScreenHorrorMoviesUseCase
import com.example.moviespetapp.domain.GetMainScreenKidMoviesUseCase
import com.example.moviespetapp.domain.GetMainScreenNewMoviesUseCase
import com.example.moviespetapp.domain.GetMainScreenPopularMoviesUseCase
import com.example.moviespetapp.domain.GetMainScreenSoonMoviesUseCase
import com.example.moviespetapp.domain.GetMoviesByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            _newMovies.value = getMainScreenNewMoviesUseCase.getMovies()
            _soonMovies.value = getMainScreenSoonMoviesUseCase.getMovies()
            _popularMovies.value = getMainScreenPopularMoviesUseCase.getMovies()
            _fictionMovies.value = getMainScreenFictionMoviesUseCase.getMovies()
            _comedyMovies.value = getMainScreenComedyMoviesUseCase.getMovies()
            _horrorMovies.value = getMainScreenHorrorMoviesUseCase.getMovies()
            _kidMovies.value = getMainScreenKidMoviesUseCase.getMovies()
        }
    }

    fun initGenres() {
        viewModelScope.launch {
            _genres.value = getGenresUseCase.getGenres()
        }
    }


}