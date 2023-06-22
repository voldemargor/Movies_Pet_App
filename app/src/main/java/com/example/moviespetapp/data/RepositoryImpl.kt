package com.example.moviespetapp.data

import com.example.moviespetapp.data.mapper.Mapper
import com.example.moviespetapp.data.network.ApiService
import com.example.moviespetapp.data.sharedprefs.BookmarkService
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.entity.Genre
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.Repository
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
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenNewMovies(): DataLoadingResult {
        apiService.getMainScreenNewMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenSoonMovies(): DataLoadingResult {
        apiService.getMainScreenSoonMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncompleteSoonMovies(it) })
        }
    }

    override suspend fun getMainScreenPopularMovies(): DataLoadingResult {
        apiService.getMainScreenPopularMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenFictionMovies(): DataLoadingResult {
        apiService.getMainScreenFictionMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenComedyMovies(): DataLoadingResult {
        apiService.getMainScreenComedyMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenHorrorMovies(): DataLoadingResult {
        apiService.getMainScreenHorrorMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenKidMovies(): DataLoadingResult {
        apiService.getMainScreenKidMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getBookedMovies(ids: Array<String>, page: Int): DataLoadingResult {
        apiService.getBookedMovies(ids, page).apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val movies = body()?.movies ?: listOf()
            return DataLoadingResult.Success(movies)
        }
    }

    override suspend fun getMoviesBySearch(request: String, page: Int): DataLoadingResult {
        apiService.getMoviesBySearch(request, page).apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))

            val listDto = body()?.movies
            val listEntity = Mapper.mapMovieSearchListDtoToListEntity(listDto) ?: listOf()
            return DataLoadingResult.Success(listEntity.filter { filterSearchResult(it) })
        }
    }

    //override fun getBookedMovies(): LiveData<List<Movie>> {
    //    return MutableLiveData(
    //        listOf(
    //            Movie(
    //                year = 2222,
    //                name = "Фильм в закладках",
    //                alternativeName = "Alternative Name",
    //                description = "description",
    //                shortDescription = "short description",
    //                poster = Poster("url"),
    //                rating = Rating(kp = 5.0, 9.2),
    //                trailers = null,
    //                genres = null,
    //                votes = Votes(kp = "32150", imdb = "45150"),
    //                country = Country("Россия"),
    //                ageRating = 18,
    //                movieLength = 166,
    //                similarMovies = listOf()
    //            )))
    //}

    override suspend fun getMovieDetails(id: Int): Movie {
        return Mapper.mapDtoToEntity(apiService.getMovieDetails(movieId = id))
    }

    override suspend fun addBookmark(movie: Movie) {
        bookmarkService.addToBookmarks(movie.id)
    }

    override suspend fun removeBookmark(movie: Movie) {
        bookmarkService.removeBookmark(movie.id)
    }

    override suspend fun getGenres(): List<Genre> {
        return Mapper.mapGenresListDtoToListEntity(apiService.getGenres())
    }

    private fun filterIncomplete(movie: Movie) =
        movie.poster != null && movie.name != null && movie.description != null && movie.rating != null

    private fun filterIncompleteSoonMovies(movie: Movie) =
        movie.poster != null && movie.name != null && movie.description != null

    private fun filterSearchResult(movie: Movie) =
        movie.poster != null && movie.name != null && movie.rating != null

}