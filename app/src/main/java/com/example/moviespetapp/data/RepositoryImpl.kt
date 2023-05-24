package com.example.moviespetapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviespetapp.data.mapper.MovieMapper
import com.example.moviespetapp.data.network.ApiService
import com.example.moviespetapp.domain.Genre
import com.example.moviespetapp.domain.Movie
import com.example.moviespetapp.domain.Poster
import com.example.moviespetapp.domain.Rating
import com.example.moviespetapp.domain.Repository
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getMoviesForGenre(genreName: String): Pair<List<Movie>, Exception?> {
        //return apiService.getMoviesForGenre(genres = genreName).movies
        apiService.getMoviesForGenre(genres = genreName).apply {
            if (!isSuccessful)
                return Pair(listOf(), ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return Pair(movies, null)
        }
    }

    override fun getFavMovies(): LiveData<List<Movie>> {
        return MutableLiveData(
            listOf(
                Movie(
                    2222,
                    "Фильм в закладках",
                    "description",
                    Poster("url"),
                    Rating(kp = 5.0))))
    }

    override suspend fun getMovie(id: Int): Movie {
        return MovieMapper.mapDtoToEntity(apiService.getMovie(movieId = id))
    }

    override suspend fun addToFavorites(movie: Movie): Long {
        return 111
    }

    override suspend fun deleteFromFavorites(movie: Movie) {
    }

    override suspend fun getGenres(): List<Genre> {
        return MovieMapper.mapListDtoToListEntity(apiService.getGenres())
    }
}