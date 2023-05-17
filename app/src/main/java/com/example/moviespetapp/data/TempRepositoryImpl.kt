package com.example.moviespetapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviespetapp.domain.Genre
import com.example.moviespetapp.domain.Movie
import com.example.moviespetapp.domain.Poster
import com.example.moviespetapp.domain.Rating
import com.example.moviespetapp.domain.Repository
import com.github.javafaker.Faker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TempRepositoryImpl @Inject constructor()  {
//class TempRepositoryImpl @Inject constructor() : Repository {
//
//    private val faker: Faker = Faker.instance()
//
//    private var movies = mutableListOf<Movie>()
//    private val moviesLD = MutableLiveData<List<Movie>>()
//
//    private var favMovies = mutableListOf<Movie>()
//    private val favMoviesLD = MutableLiveData<List<Movie>>()
//
//    init {
//        POSTERS.shuffle()
//        movies = (1..100).map {
//            Movie(
//                id = it,
//                name = faker.name().title(),
//                year = faker.number().numberBetween(1989, 2023),
//                poster = POSTERS[it % POSTERS.size],
//                description = faker.lorem().paragraph(),
//                rating = Rating(faker.number().randomDigitNotZero().toDouble())
//                //name = "name",
//                //year = 2015,
//                //poster = POSTERS[it % POSTERS.size],
//                //description = "description",
//                //rating = Rating(4.toDouble())
//            )
//        }.toMutableList()
//        moviesLD.value = movies.toList() // возвращаем копию
//    }
//
//    override fun getMoviesForGenre(genreName: String): LiveData<List<Movie>> {
//        return moviesLD
//    }
//
//    override fun getFavMovies(): LiveData<List<Movie>> {
//        return favMoviesLD
//    }
//
//    override suspend fun getMovie(id: Int): Movie {
//        return movies.find { it.id == id } ?: throw RuntimeException("Movie id $id not found")
//    }
//
//    override suspend fun addToFavorites(movie: Movie): Long {
//        favMovies.add(movie)
//        updateFavMoviesLD()
//        return movie.id.toLong()
//    }
//
//    override suspend fun deleteFromFavorites(movie: Movie) {
//        favMovies.remove(movie)
//        updateFavMoviesLD()
//    }
//
//    override suspend fun getGenres(): List<Genre> {
//        TODO("Not yet implemented")
//    }
//
//    private fun updateFavMoviesLD() {
//        favMoviesLD.value = favMovies.toList() // возвращаем копию
//    }
//
//    companion object {
//        private val POSTERS = mutableListOf(
//            Poster("https://avatars.mds.yandex.net/get-entity_search/118114/609591715/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/517208/612661925/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/1380855/612628375/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/1938771/608875838/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/2223725/473325039/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/2304479/532945747/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/6827443/646921388/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/8076986/648229858/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/1976572/604025508/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/2320096/532945587/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/2331646/472462834/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/5735732/543085795/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/7689070/628190240/S168x252_2x"),
//            Poster("https://avatars.mds.yandex.net/get-entity_search/7980340/647325503/S168x252_2x"),
//        )
//    }

}