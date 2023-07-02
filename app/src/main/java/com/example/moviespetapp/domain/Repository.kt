package com.example.moviespetapp.domain

import com.example.moviespetapp.domain.entity.Genre
import com.example.moviespetapp.domain.entity.Movie

interface Repository {

    suspend fun getMainScreenGenres(): List<Genre>

    suspend fun getMainScreenNewMovies(): DataLoadingResult

    suspend fun getMainScreenSoonMovies(): DataLoadingResult

    suspend fun getMainScreenPopularMovies(): DataLoadingResult

    suspend fun getMainScreenFictionMovies(): DataLoadingResult

    suspend fun getMainScreenComedyMovies(): DataLoadingResult

    suspend fun getMainScreenHorrorMovies(): DataLoadingResult

    suspend fun getMainScreenKidMovies(): DataLoadingResult


    suspend fun getMoviesByGenre(genreName: String, page: Int): DataLoadingResult

    suspend fun getMoviesBySearch(request: String, page: Int): DataLoadingResult

    suspend fun getBookedMovies(ids: Array<String>, page: Int): DataLoadingResult


    suspend fun getMovieDetails(id: Int): Movie

    suspend fun addBookmark(movie: Movie)

    suspend fun removeBookmark(movie: Movie)


}