package com.example.moviespetapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviespetapp.data.mapper.MovieMapper
import com.example.moviespetapp.data.network.ApiService
import com.example.moviespetapp.domain.Country
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.Genre
import com.example.moviespetapp.domain.Movie
import com.example.moviespetapp.domain.Poster
import com.example.moviespetapp.domain.Rating
import com.example.moviespetapp.domain.Repository
import com.example.moviespetapp.domain.Votes
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
                    year = 2222,
                    name = "Фильм в закладках",
                    alternativeName = "Alternative Name",
                    description = "description",
                    poster = Poster("url"),
                    rating = Rating(kp = 5.0, 9.2),
                    trailers = null,
                    genres = null,
                    votes = Votes(kp = "32150", imdb = "45150"),
                    country = Country("Россия"),
                    ageRating = 18,
                    movieLength = 166,
                    similarMovies = listOf()
                )))
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