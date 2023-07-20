package com.example.moviespetapp.data.network

import com.example.moviespetapp.Constants
import com.example.moviespetapp.data.network.model.GenreDto
import com.example.moviespetapp.data.network.model.MovieDto
import com.example.moviespetapp.data.network.model.ResponseListMovieDto
import com.example.moviespetapp.data.network.model.ResponseListMovieSearchResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/movie/possible-values-by-field?field=genres.name")
    suspend fun getGenres(
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): List<GenreDto>

    @GET("/v1.3/movie/{id}")
    suspend fun getMovieDetails(
        @Path(QUERY_PARAM_MOVIE_ID) movieId: Int,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): MovieDto

    @GET("/v1.3/movie")
    suspend fun getMoviesByGenre(
        @Query(QUERY_PARAM_GENRES_NAME) genres: String,
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_SORT_FIELD) sortField1: String = "year",
        @Query(QUERY_PARAM_SORT_FIELD) sortField2: String = "votes.kp",
        @Query(QUERY_PARAM_SORT_FIELD) sortField3: String = "rating.kp",
        @Query(QUERY_PARAM_VOTES_KP) votes: String = Constants.QUERY_VOTES_GENRE_MOVIES,
        @Query(QUERY_PARAM_RATING_KP) rating: String = Constants.QUERY_RATING_GENRE_MOVIES,
        @Query(QUERY_PARAM_LIMIT) limit: Int = Constants.QUERY_PAGE_LIMIT,
        @Query(QUERY_PARAM_SELECT_FIELDS) selectFields: Array<String> = Constants.QUERY_SELECT_FIELDS,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseListMovieDto>

    @GET("/v1.3/movie")
    suspend fun getMainScreenNewMovies(
        @Query(QUERY_PARAM_PAGE) page: Int = 1,
        @Query(QUERY_PARAM_SORT_FIELD) sortField: String = "premiere.world",
        @Query(QUERY_PARAM_VOTES_KP) votes: String = "100-9999999",
        @Query(QUERY_PARAM_RATING_KP) rating: String = "5-10",
        @Query(QUERY_PARAM_LIMIT) limit: Int = Constants.ITEMS_COUNT_FOR_MAIN_SCREEN,
        @Query(QUERY_PARAM_SELECT_FIELDS) selectFields: Array<String> = Constants.QUERY_SELECT_FIELDS,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseListMovieDto>

    @GET("/v1.3/movie")
    suspend fun getMainScreenSoonMovies(
        @Query(QUERY_PARAM_PAGE) page: Int = 1,
        @Query("year") year: String = "2023-2024",
        @Query("votes.kp") votes: String = "0",
        @Query("rating.await") ratingAwait: String = "10-100",
        @Query(QUERY_PARAM_SORT_FIELD) sortField1: String = "year",
        @Query("sortType") sortType1: String = "1",
        @Query(QUERY_PARAM_SORT_FIELD) sortField2: String = "votes.await",
        //@Query(QUERY_PARAM_SORT_FIELD) sortField3: String = "rating.await",
        //@Query("sortType") sortType2: String = "1",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 30,
        @Query(QUERY_PARAM_SELECT_FIELDS) selectFields: Array<String> = Constants.QUERY_SELECT_FIELDS,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseListMovieDto>

    @GET("/v1.3/movie")
    suspend fun getMainScreenPopularMovies(
        @Query(QUERY_PARAM_PAGE) page: Int = 1,
        @Query(QUERY_PARAM_SORT_FIELD) sortField1: String = "year",
        @Query(QUERY_PARAM_SORT_FIELD) sortField2: String = "votes.kp",
        @Query(QUERY_PARAM_VOTES_KP) votes: String = "100000-9999999",
        @Query(QUERY_PARAM_RATING_KP) rating: String = "5-10",
        @Query(QUERY_PARAM_LIMIT) limit: Int = Constants.ITEMS_COUNT_FOR_MAIN_SCREEN,
        @Query(QUERY_PARAM_SELECT_FIELDS) selectFields: Array<String> = Constants.QUERY_SELECT_FIELDS,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseListMovieDto>

    @GET("/v1.3/movie")
    suspend fun getMainScreenFictionMovies(
        @Query(QUERY_PARAM_GENRES_NAME) genres: String = "фантастика",
        @Query(QUERY_PARAM_PAGE) page: Int = 1,
        @Query(QUERY_PARAM_SORT_FIELD) sortField1: String = "year",
        @Query(QUERY_PARAM_SORT_FIELD) sortField2: String = "votes.kp",
        @Query(QUERY_PARAM_VOTES_KP) votes: String = Constants.QUERY_VOTES_GENRE_MOVIES,
        @Query(QUERY_PARAM_RATING_KP) rating: String = Constants.QUERY_RATING_GENRE_MOVIES,
        @Query(QUERY_PARAM_LIMIT) limit: Int = Constants.ITEMS_COUNT_FOR_MAIN_SCREEN,
        @Query(QUERY_PARAM_SELECT_FIELDS) selectFields: Array<String> = Constants.QUERY_SELECT_FIELDS,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseListMovieDto>

    @GET("/v1.3/movie")
    suspend fun getMainScreenComedyMovies(
        @Query(QUERY_PARAM_GENRES_NAME) genres: String = "комедия",
        @Query(QUERY_PARAM_PAGE) page: Int = 1,
        @Query(QUERY_PARAM_SORT_FIELD) sortField1: String = "year",
        @Query(QUERY_PARAM_SORT_FIELD) sortField2: String = "votes.kp",
        @Query(QUERY_PARAM_VOTES_KP) votes: String = Constants.QUERY_VOTES_GENRE_MOVIES,
        @Query(QUERY_PARAM_RATING_KP) rating: String = Constants.QUERY_RATING_GENRE_MOVIES,
        @Query(QUERY_PARAM_LIMIT) limit: Int = Constants.ITEMS_COUNT_FOR_MAIN_SCREEN,
        @Query(QUERY_PARAM_SELECT_FIELDS) selectFields: Array<String> = Constants.QUERY_SELECT_FIELDS,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseListMovieDto>

    @GET("/v1.3/movie")
    suspend fun getMainScreenHorrorMovies(
        @Query(QUERY_PARAM_GENRES_NAME) genres: String = "ужасы",
        @Query(QUERY_PARAM_PAGE) page: Int = 1,
        @Query(QUERY_PARAM_SORT_FIELD) sortField: String = "year",
        @Query(QUERY_PARAM_SORT_FIELD) sortField2: String = "votes.kp",
        @Query(QUERY_PARAM_VOTES_KP) votes: String = Constants.QUERY_VOTES_GENRE_MOVIES,
        @Query(QUERY_PARAM_RATING_KP) rating: String = Constants.QUERY_RATING_GENRE_MOVIES,
        @Query(QUERY_PARAM_LIMIT) limit: Int = Constants.ITEMS_COUNT_FOR_MAIN_SCREEN,
        @Query(QUERY_PARAM_SELECT_FIELDS) selectFields: Array<String> = Constants.QUERY_SELECT_FIELDS,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseListMovieDto>

    @GET("/v1.3/movie")
    suspend fun getMainScreenKidMovies(
        @Query(QUERY_PARAM_GENRES_NAME) genres1: String = "детский",
        @Query(QUERY_PARAM_GENRES_NAME) genres2: String = "семейный",
        @Query(QUERY_PARAM_PAGE) page: Int = 1,
        @Query(QUERY_PARAM_SORT_FIELD) sortField: String = "year",
        @Query(QUERY_PARAM_SORT_FIELD) sortField2: String = "votes.kp",
        @Query(QUERY_PARAM_VOTES_KP) votes: String = Constants.QUERY_VOTES_GENRE_MOVIES,
        @Query(QUERY_PARAM_RATING_KP) rating: String = Constants.QUERY_RATING_GENRE_MOVIES,
        @Query(QUERY_PARAM_LIMIT) limit: Int = Constants.ITEMS_COUNT_FOR_MAIN_SCREEN,
        @Query(QUERY_PARAM_SELECT_FIELDS) selectFields: Array<String> = Constants.QUERY_SELECT_FIELDS,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseListMovieDto>

    @GET("/v1.3/movie")
    suspend fun getBookedMovies(
        @Query(QUERY_PARAM_MOVIE_ID) idsArray: Array<String>,
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_LIMIT) limit: Int = Constants.QUERY_PAGE_LIMIT,
        @Query(QUERY_PARAM_SELECT_FIELDS) selectFields: Array<String> = Constants.QUERY_SELECT_FIELDS,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseListMovieDto>

    @GET("/v1.2/movie/search")
    suspend fun getMoviesBySearch(
        @Query(QUERY_PARAM_SEARCH) query: String,
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_LIMIT) limit: Int = Constants.QUERY_SEARCH_PAGE_LIMIT,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseListMovieSearchResultDto>

    companion object {
        private const val QUERY_PARAM_API_TOKEN = "token"
        private const val QUERY_PARAM_SELECT_FIELDS = "selectFields"
        private const val QUERY_PARAM_MOVIE_ID = "id"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TYPE = "type"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_SORT_FIELD = "sortField"
        private const val QUERY_PARAM_GENRES_NAME = "genres.name"
        private const val QUERY_PARAM_VOTES_KP = "votes.kp"
        private const val QUERY_PARAM_RATING_KP = "rating.kp"
        private const val QUERY_PARAM_SEARCH = "query"
        private const val QUERY_PARAM_SEARCH_ALT_NAME = "alternativeName"
    }

}