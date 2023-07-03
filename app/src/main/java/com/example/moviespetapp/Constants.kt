package com.example.moviespetapp

class Constants {

    companion object {

        const val BASE_URL = "https://api.kinopoisk.dev/"
        const val API_TOKEN = "3CA6B3S-QWRM43Y-KQT23M8-BC2XM9A"
        const val QUERY_PAGE_LIMIT = 21

        const val QUERY_SEARCH_PAGE_LIMIT = 80
        const val QUERY_VOTES_GENRE_MOVIES = "1000-9999999"
        const val QUERY_RATING_GENRE_MOVIES = "5-10"
        const val ITEMS_BEFORE_CALL_REACH_END = 6
        const val ITEMS_COUNT_FOR_MAIN_SCREEN = 20

        val QUERY_SELECT_FIELDS = arrayOf(
            "id",
            "name",
            "alternativeName",
            "description",
            "shortDescription",
            "year",
            "poster",
            "rating",
            "videos",
            "genres",
            "votes",
            "countries",
            "movieLength",
            "ageRating",
            "similarMovies",
        )

    }

}