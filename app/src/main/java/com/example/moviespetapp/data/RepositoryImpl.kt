package com.example.moviespetapp.data

import com.example.moviespetapp.data.mapper.Mapper
import com.example.moviespetapp.data.network.ApiService
import com.example.moviespetapp.data.sharedprefs.BookmarkService
import com.example.moviespetapp.domain.DataLoadingResult
import com.example.moviespetapp.domain.Repository
import com.example.moviespetapp.domain.entity.Genre
import com.example.moviespetapp.domain.entity.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(

    private val apiService: ApiService,
    private val bookmarkService: BookmarkService,

    ) : Repository {

    override suspend fun getMainScreenGenres(): List<Genre> {
        return Mapper.mapMainScreenListGenreDtoToListEntity(apiService.getGenres())
    }

    override suspend fun getMainScreenNewMovies(): DataLoadingResult {
        apiService.getMainScreenNewMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val moviesDto = body()?.movies
            val movies = Mapper.mapListMovieDtoToListEntity(moviesDto) ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenSoonMovies(): DataLoadingResult {
        apiService.getMainScreenSoonMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val moviesDto = body()?.movies
            val movies = Mapper.mapListMovieDtoToListEntity(moviesDto) ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncompleteSoonMovies(it) })
        }
    }

    override suspend fun getMainScreenPopularMovies(): DataLoadingResult {
        apiService.getMainScreenPopularMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val moviesDto = body()?.movies
            val movies = Mapper.mapListMovieDtoToListEntity(moviesDto) ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenFictionMovies(): DataLoadingResult {
        apiService.getMainScreenFictionMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val moviesDto = body()?.movies
            val movies = Mapper.mapListMovieDtoToListEntity(moviesDto) ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenComedyMovies(): DataLoadingResult {
        apiService.getMainScreenComedyMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val moviesDto = body()?.movies
            val movies = Mapper.mapListMovieDtoToListEntity(moviesDto) ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenHorrorMovies(): DataLoadingResult {
        apiService.getMainScreenHorrorMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val moviesDto = body()?.movies
            val movies = Mapper.mapListMovieDtoToListEntity(moviesDto) ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMainScreenKidMovies(): DataLoadingResult {
        apiService.getMainScreenKidMovies().apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val moviesDto = body()?.movies
            val movies = Mapper.mapListMovieDtoToListEntity(moviesDto) ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMoviesByGenre(genreName: String, page: Int): DataLoadingResult {
        apiService.getMoviesByGenre(genres = genreName, page).apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val moviesDto = body()?.movies
            val movies = Mapper.mapListMovieDtoToListEntity(moviesDto) ?: listOf()
            return DataLoadingResult.Success(movies.filter { filterIncomplete(it) })
        }
    }

    override suspend fun getMoviesBySearch(request: String, page: Int): DataLoadingResult {
        apiService.getMoviesBySearch(request, page).apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val listDto = body()?.movies
            val listEntity = Mapper.mapListMovieSearchDtoToListEntity(listDto) ?: listOf()
            return DataLoadingResult.Success(listEntity.filter { filterSearchResult(it) })
        }
    }

    override suspend fun getBookedMovies(ids: Array<String>, page: Int): DataLoadingResult {
        apiService.getBookedMovies(ids, page).apply {
            if (!isSuccessful)
                return DataLoadingResult.Failed(ApiLoadingException("Code ${code()}: ${message()}"))
            val moviesDto = body()?.movies
            val movies = Mapper.mapListMovieDtoToListEntity(moviesDto) ?: listOf()
            return DataLoadingResult.Success(movies)
        }
    }

    override suspend fun getMovieDetails(id: Int): Movie {
        return Mapper.mapDtoToEntity(apiService.getMovieDetails(movieId = id))
    }

    override suspend fun addBookmark(movie: Movie) {
        bookmarkService.addToBookmarks(movie.id)
    }

    override suspend fun removeBookmark(movie: Movie) {
        bookmarkService.removeBookmark(movie.id)
    }

    private fun filterIncomplete(movie: Movie) = with(movie) {
        poster != null && name != null && description != null && rating != null
    }

    private fun filterIncompleteSoonMovies(movie: Movie) = with(movie) {
        poster != null && name != null && description != null
    }

    private fun filterSearchResult(movie: Movie) = with(movie) {
        !name.isNullOrBlank() && poster != null && !description.isNullOrBlank() && description.isNotEmpty()
        //poster != null && name != null && rating != null
        //        && !description.isNullOrBlank() && description.isNotEmpty()
    }
}