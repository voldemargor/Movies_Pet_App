package com.example.moviespetapp.data.mapper

import com.example.moviespetapp.data.network.model.*
import com.example.moviespetapp.domain.entity.*

object Mapper {

    fun mapDtoToEntity(dto: MovieDto) = Movie(
        id = dto.id,
        name = dto.name,
        alternativeName = dto.alternativeName,
        description = dto.description,
        shortDescription = dto.shortDescription,
        year = dto.year,
        poster = mapDtoToEntity(dto.poster),
        rating = mapDtoToEntity(dto.rating),
        trailers = mapListTrailerDtoToListEntity(dto.videos?.trailers),
        genres = mapListGenreDtoToListEntity(dto.genres),
        votes = mapDtoToEntity(dto.votes),
        country = mapDtoToEntity(dto.countries),
        movieLength = dto.movieLength,
        ageRating = dto.ageRating,
        similarMovies = mapSimilarMoviesDtoToListEntity(dto.similarMovies)
    )

    private fun mapDtoToEntity(dto: GenreDto) = Genre(
        name = dto.name,
        //slug = dto.slug
    )

    private fun mapDtoToEntity(dto: List<CountryDto>?): Country {
        if (dto.isNullOrEmpty()) return Country("")
        return Country(name = dto[0].name)
    }

    private fun mapDtoToEntity(dto: TrailerDto) = Trailer(
        url = dto.url,
        name = dto.name,
        site = dto.site,
        type = dto.type,
    )

    private fun mapDtoToEntity(dto: VotesDto) = Votes(
        kp = dto.kp,
        imdb = dto.imdb
    )

    private fun mapDtoToEntity(dto: RatingDto) = Rating(
        kp = dto.kp,
        imdb = dto.imdb,
    )

    private fun mapDtoToEntity(dto: PosterDto?): Poster? {
        if (dto == null) return null
        return Poster(url = dto.url)
    }

    private fun mapDtoToEntity(dto: MovieSimilarDto) = MovieSimilar(
        id = dto.id,
        name = dto.name,
        poster = mapDtoToEntity(dto.poster)
    )

    private fun mapDtoToEntity(dto: MovieSearchResultDto) = Movie(
        id = dto.id,
        year = dto.year,
        name = dto.name,
        alternativeName = dto.alternativeName,
        poster = dto.poster?.let { Poster(it) },
        rating = dto.rating?.let { Rating(it, 0.0) },
        description = dto.description,
        shortDescription = null,
        trailers = null,
        genres = null,
        votes = Votes("0", "0"),
        country = Country(""),
        movieLength = null,
        ageRating = null,
        similarMovies = null
    )

    fun mapListGenreDtoToListEntity(listDto: List<GenreDto>?): List<Genre>? {
        listDto ?: return null
        return listDto.map { mapDtoToEntity(it) }
    }

    fun mapMainScreenListGenreDtoToListEntity(listDto: List<GenreDto>): List<Genre> {
        val listEntityFull = listDto.map { mapDtoToEntity(it) }
        val listToRemove = listOf(
            Genre("для взрослых"),
            Genre("концерт"),
            Genre("короткометражка"),
            Genre("музыка"),
            Genre("новости"),
            Genre("реальное ТВ"),
            Genre("ток-шоу"),
            Genre("церемония"),
            Genre("игра"),
            Genre("фильм-нуар"),
            Genre("криминал"),
            Genre("драма"),
            Genre("биография"),
            Genre("мюзикл"),
            Genre("документальный"),
        )
        return listEntityFull.minus(listToRemove.toSet())
    }

    fun mapListMovieDtoToListEntity(listDto: List<MovieDto>?): List<Movie>? {
        return listDto?.map { mapDtoToEntity(it) }
    }

    fun mapListMovieSearchDtoToListEntity(listDto: List<MovieSearchResultDto>?): List<Movie>? {
        return listDto?.map { mapDtoToEntity(it) }
    }

    private fun mapListTrailerDtoToListEntity(listDto: List<TrailerDto>?): List<Trailer>? {
        return listDto?.map { mapDtoToEntity(it) }
    }

    private fun mapSimilarMoviesDtoToListEntity(listDto: List<MovieSimilarDto>?): List<MovieSimilar>? {
        return listDto?.map { mapDtoToEntity(it) }
    }

}