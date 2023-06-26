package com.example.moviespetapp.presentation.mainscreen

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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    private val _displayLoader = MutableLiveData<Boolean>()
    val displayLoader: LiveData<Boolean> get() = _displayLoader

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

    fun loadSectionsData() {

        // А ВОТ ТЕПЕРЬ ЭТО РАЗНЫЕ ДЖОБЫ ПАРАЛЛЕЛЬНО !!!!

        val jobs = mutableListOf<Job>()

        jobs.add(viewModelScope.launch(Dispatchers.IO) {
            val genres = getGenresUseCase.getGenres()
            withContext(Dispatchers.Main) { _genres.value = genres }
        })
        jobs.add(viewModelScope.launch(Dispatchers.IO) {
            val newMovies = getMainScreenNewMoviesUseCase.getMovies()
            withContext(Dispatchers.Main) { _newMovies.value = newMovies }
        })
        jobs.add(viewModelScope.launch(Dispatchers.IO) {
            val soonMovies = getMainScreenSoonMoviesUseCase.getMovies()
            withContext(Dispatchers.Main) { _soonMovies.value = soonMovies }
        })
        jobs.add(viewModelScope.launch(Dispatchers.IO) {
            val popularMovies = getMainScreenPopularMoviesUseCase.getMovies()
            withContext(Dispatchers.Main) { _popularMovies.value = popularMovies }
        })
        jobs.add(viewModelScope.launch(Dispatchers.IO) {
            val fictionMovies = getMainScreenFictionMoviesUseCase.getMovies()
            withContext(Dispatchers.Main) { _fictionMovies.value = fictionMovies }
        })
        jobs.add(viewModelScope.launch(Dispatchers.IO) {
            val comedyMovies = getMainScreenComedyMoviesUseCase.getMovies()
            withContext(Dispatchers.Main) { _comedyMovies.value = comedyMovies }
        })
        jobs.add(viewModelScope.launch(Dispatchers.IO) {
            val horrorMovies = getMainScreenHorrorMoviesUseCase.getMovies()
            withContext(Dispatchers.Main) { _horrorMovies.value = horrorMovies }
        })
        jobs.add(viewModelScope.launch(Dispatchers.IO) {
            val kidMovies = getMainScreenKidMoviesUseCase.getMovies()
            withContext(Dispatchers.Main) { _kidMovies.value = kidMovies }
        })

        val parentJob = viewModelScope.launch {
            _displayLoader.value = true
            for (job in jobs) job.join()
            _displayLoader.value = false
            delay(1200)
        }
    }

}