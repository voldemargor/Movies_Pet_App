package com.example.moviespetapp.domain

import androidx.lifecycle.LiveData

interface Repository {

    suspend fun getMoviesByGenre(genreName: String, page: Int): DataLoadingResult

    suspend fun getMainScreenNewMovies(): DataLoadingResult

    suspend fun getMainScreenSoonMovies(): DataLoadingResult

    suspend fun getMainScreenPopularMovies(): DataLoadingResult

    suspend fun getMainScreenFictionMovies(): DataLoadingResult

    suspend fun getMainScreenComedyMovies(): DataLoadingResult

    suspend fun getMainScreenHorrorMovies(): DataLoadingResult

    suspend fun getMainScreenKidMovies(): DataLoadingResult

    fun getFavMovies(): LiveData<List<Movie>>

    suspend fun getMovie(id: Int): Movie

    suspend fun addToFavorites(movie: Movie): Long

    suspend fun deleteFromFavorites(movie: Movie)

    suspend fun getGenres(): List<Genre>


}