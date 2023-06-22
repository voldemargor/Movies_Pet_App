package com.example.moviespetapp.data.mapper

import com.example.moviespetapp.data.network.model.CountryDto
import com.example.moviespetapp.data.network.model.GenreDto
import com.example.moviespetapp.data.network.model.MovieDto
import com.example.moviespetapp.data.network.model.MovieSearchResultDto
import com.example.moviespetapp.data.network.model.MovieSimilarDto
import com.example.moviespetapp.data.network.model.PosterDto
import com.example.moviespetapp.data.network.model.RatingDto
import com.example.moviespetapp.data.network.model.TrailerDto
import com.example.moviespetapp.data.network.model.VotesDto
import com.example.moviespetapp.domain.entity.Country
import com.example.moviespetapp.domain.entity.Genre
import com.example.moviespetapp.domain.entity.Movie
import com.example.moviespetapp.domain.entity.MovieSimilar
import com.example.moviespetapp.domain.entity.Poster
import com.example.moviespetapp.domain.entity.Rating
import com.example.moviespetapp.domain.entity.Trailer
import com.example.moviespetapp.domain.entity.Votes

object Mapper {

    //fun mapEntityToDtoModel(entity: Movie) = MovieInfoDto(
    //    id = entity.id,
    //    name = entity.name,
    //    description = entity.description,
    //    year = entity.year,
    //    poster = entity.poster,
    //    rating = entity.rating
    //)

    //fun mapEntityToDtoModel(entity: Genre) = GenreDto(
    //    name = entity.name,
    //    //slug = entity.slug
    //)

    fun mapDtoToEntity(dto: MovieDto) = Movie(
        id = dto.id,
        name = dto.name,
        alternativeName = dto.alternativeName,
        description = dto.description,
        shortDescription = dto.shortDescription,
        year = dto.year,
        poster = mapDtoToEntity(dto.poster),
        rating = mapDtoToEntity(dto.rating),
        trailers = mapTrailersListDtoToListEntity(dto.videos?.trailers),
        genres = mapGenresListDtoToListEntity(dto.genres ?: listOf()),
        votes = mapDtoToEntity(dto.votes),
        country = mapDtoToEntity(dto.countries[0]),
        movieLength = dto.movieLength,
        ageRating = dto.ageRating,
        similarMovies = mapSimilarMoviesDtoToListEntity(dto.similarMovies)
    )

    private fun mapDtoToEntity(dto: GenreDto) = Genre(
        name = dto.name,
        //slug = dto.slug
    )

    private fun mapDtoToEntity(dto: CountryDto) = Country(
        name = dto.name,
    )

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

    //private fun mapDtoToEntity(dto: MovieSearchResultDto) = MovieSearchResult(
    //    id = dto.id,
    //    year = dto.year,
    //    name = dto.name,
    //    alternativeName = dto.alternativeName,
    //    poster = dto.poster,
    //    rating = dto.rating,
    //)

    private fun mapDtoToEntity(dto: MovieSearchResultDto) = Movie(
        id = dto.id,
        year = dto.year,
        name = dto.name,
        alternativeName = dto.alternativeName,
        poster = dto.poster?.let { Poster(it) },
        rating = dto.rating?.let { Rating(it, 0.0) },
        description = null,
        shortDescription = null,
        trailers = null,
        genres = null,
        votes = Votes("0", "0"),
        country = Country(""),
        movieLength = null,
        ageRating = null,
        similarMovies = null
    )

    fun mapGenresListDtoToListEntity(listDto: List<GenreDto>): List<Genre> {
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
        )
        return listEntityFull.minus(listToRemove.toSet())
    }

    fun mapMovieSearchListDtoToListEntity(listDto: List<MovieSearchResultDto>?): List<Movie>? {
        return listDto?.map { mapDtoToEntity(it) }
    }

    fun mapBookedListDtoToListEntity(listDto: List<MovieDto>?): List<Movie>? {
        return listDto?.map { mapDtoToEntity(it) }
    }

    private fun mapTrailersListDtoToListEntity(listDto: List<TrailerDto>?): List<Trailer>? {
        return listDto?.map { mapDtoToEntity(it) }
    }

    private fun mapSimilarMoviesDtoToListEntity(listDto: List<MovieSimilarDto>?): List<MovieSimilar>? {
        return listDto?.map { mapDtoToEntity(it) }
    }


}