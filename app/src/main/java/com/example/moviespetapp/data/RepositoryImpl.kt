package com.example.moviespetapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviespetapp.data.mapper.MovieMapper
import com.example.moviespetapp.data.network.ApiService
import com.example.moviespetapp.data.sharedprefs.BookmarkService
import com.example.moviespetapp.domain.entity.Country
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.entity.Genre
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.entity.Poster
import com.example.moviespetapp.domain.entity.Rating
import com.example.moviespetapp.domain.Repository
import com.example.moviespetapp.domain.entity.Votes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(

    private val apiService: ApiService,
    private val bookmarkService: BookmarkService,
    //private val app: App

) : Repository {

    override suspend fun getMoviesByGenre(genreName: String, page: Int): DataLoadingResult {
        apiService.getMoviesByGenre(genres = genreName, page).apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncompleted(it) })
        }
    }

    override suspend fun getMainScreenNewMovies(): DataLoadingResult {
        apiService.getMainScreenNewMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncompleted(it) })
        }
    }

    override suspend fun getMainScreenSoonMovies(): DataLoadingResult {
        apiService.getMainScreenSoonMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncompleted(it) })
        }
    }

    override suspend fun getMainScreenPopularMovies(): DataLoadingResult {
        apiService.getMainScreenPopularMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncompleted(it) })
        }
    }

    override suspend fun getMainScreenFictionMovies(): DataLoadingResult {
        apiService.getMainScreenFictionMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncompleted(it) })
        }
    }

    override suspend fun getMainScreenComedyMovies(): DataLoadingResult {
        apiService.getMainScreenComedyMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncompleted(it) })
        }
    }

    override suspend fun getMainScreenHorrorMovies(): DataLoadingResult {
        apiService.getMainScreenHorrorMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncompleted(it) })
        }
    }

    override suspend fun getMainScreenKidMovies(): DataLoadingResult {
        apiService.getMainScreenKidMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncompleted(it) })
        }
    }

    override fun getBookmarkMovies(): LiveData<List<Movie>> {
        return MutableLiveData(
            listOf(
                Movie(
                    year = 2222,
                    name = "Фильм в закладках",
                    alternativeName = "Alternative Name",
                    description = "description",
                    shortDescription = "short description",
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

    override suspend fun addBookmark(movie: Movie) {
        bookmarkService.addToBookmarks(movie.id)
    }

    override suspend fun removeBookmark(movie: Movie) {
        bookmarkService.removeBookmark(movie.id)
    }

    override suspend fun getGenres(): List<Genre> {
        return MovieMapper.mapGenresListDtoToListEntity(apiService.getGenres())
    }

    private fun filterIncompleted(movie: Movie) =
        movie.poster != null && movie.name != null && movie.description != null
}