package com.example.moviespetapp.data.network

import com.example.moviespetapp.data.network.model.GenreDto
import com.example.moviespetapp.data.network.model.MovieInfoDto
import com.example.moviespetapp.data.network.model.ResponseMoviesListDto
import com.example.moviespetapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v1.3/movie")
    suspend fun getMoviesForGenre(
        @Query(QUERY_PARAM_GENRES_NAME) genres: String,
        @Query(QUERY_PARAM_SORT_FIELD) sortField: String = "rating.kp",
        @Query(QUERY_PARAM_PAGE) page: Int = 1,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 21,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): Response<ResponseMoviesListDto>

    @GET("/v1.3/movie/{id}")
    suspend fun getMovie(
        @Path(QUERY_PARAM_MOVIE_ID) movieId: Int,
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): MovieInfoDto

    @GET("/v1/movie/possible-values-by-field?field=genres.name")
    suspend fun getGenres(
        @Query(QUERY_PARAM_API_TOKEN) apiToken: String = Constants.API_TOKEN,
    ): List<GenreDto>

    companion object {
        private const val QUERY_PARAM_API_TOKEN = "token"
        private const val QUERY_PARAM_MOVIE_ID = "id"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_SORT_FIELD = "sortField"
        private const val QUERY_PARAM_GENRES_NAME = "genres.name"
    }

}