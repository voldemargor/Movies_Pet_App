package com.example.moviespetapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviespetapp.data.mapper.MovieMapper
import com.example.moviespetapp.data.network.ApiService
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.Genre
import com.example.moviespetapp.domain.Movie
import com.example.moviespetapp.domain.Poster
import com.example.moviespetapp.domain.Rating
import com.example.moviespetapp.domain.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getMoviesForGenre(genreName: String, page: Int): DataLoadingResult {
        //return apiService.getMoviesForGenre(genres = genreName).movies
        apiService.getMoviesForGenre(genres = genreName, page).apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies)
        }
    }

    override fun getFavMovies(): LiveData<List<Movie>> {
        return MutableLiveData(
            listOf(
                Movie(
                    2222,
                    "Фильм в закладках",
                    "description",
                    poster = Poster("url"),
                    rating = Rating(kp = 5.0),
                    trailers = null)))
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
        return MovieMapper.mapGenresListDtoToListEntity(apiService.getGenres())
    }
}