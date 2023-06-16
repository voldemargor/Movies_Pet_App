package com.example.moviespetapp.data.mapper

import com.example.moviespetapp.data.network.model.CountryDto
import com.example.moviespetapp.data.network.model.GenreDto
import com.example.moviespetapp.data.network.model.MovieDto
import com.example.moviespetapp.data.network.model.MovieShortDto
import com.example.moviespetapp.data.network.model.PosterDto
import com.example.moviespetapp.data.network.model.RatingDto
import com.example.moviespetapp.data.network.model.TrailerDto
import com.example.moviespetapp.data.network.model.VotesDto
import com.example.moviespetapp.domain.Country
import com.example.moviespetapp.domain.Genre
import com.example.moviespetapp.domain.Movie
import com.example.moviespetapp.domain.MovieShort
import com.example.moviespetapp.domain.Poster
import com.example.moviespetapp.domain.Rating
import com.example.moviespetapp.domain.Trailer
import com.example.moviespetapp.domain.Votes

object MovieMapper {

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

    private fun mapDtoToEntity(dto: MovieShortDto) = MovieShort(
        id = dto.id,
        name = dto.name,
        poster = mapDtoToEntity(dto.poster)
    )

    fun mapGenresListDtoToListEntity(listDto: List<GenreDto>): List<Genre> {
        val listEntityFull = listDto.map { mapDtoToEntity(it) }
        val listToRemove = listOf(
            //Genre("для взрослых", "dlya-vzroslyh"),
            //Genre("концерт", "koncert"),
            //Genre("короткометражка", "korotkometrazhka"),
            //Genre("музыка", "muzyka"),
            //Genre("новости", "novosti"),
            //Genre("реальное ТВ", "realnoe-TV"),
            //Genre("ток-шоу", "tok-shou"),
            //Genre("церемония", "ceremoniya"),
            //Genre("игра", "igra"),
            //Genre("фильм-нуар", "film-nuar"),
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

    private fun mapTrailersListDtoToListEntity(listDto: List<TrailerDto>?): List<Trailer>? {
        return listDto?.map { mapDtoToEntity(it) }
    }

    private fun mapSimilarMoviesDtoToListEntity(listDto: List<MovieShortDto>?): List<MovieShort>? {
        return listDto?.map { mapDtoToEntity(it) }
    }


    //fun mapEntityToDtoModel(review: Review) = ReviewDto(
    //    id = folder.id,
    //    name = folder.name,
    //    itemsCompleted = folder.itemsCompleted,
    //    itemsCount = folder.itemsCount
    //)

    //fun mapDbModelToEntity(itemDbModel: ItemDbModel) = Item(
    //    id = itemDbModel.id,
    //    folderId = itemDbModel.folderId,
    //    name = itemDbModel.name,
    //    count = itemDbModel.count,
    //    enabled = itemDbModel.enabled
    //)

    //fun mapDbModelToEntity(folderDbModel: FolderDbModel) = Folder(
    //    id = folderDbModel.id,
    //    name = folderDbModel.name,
    //    itemsCompleted = folderDbModel.itemsCompleted,
    //    itemsCount = folderDbModel.itemsCount
    //)

    //fun mapListDbModelToListEntity(list: List<ItemDbModel>) = list.map {
    //    mapDbModelToEntity(it)
    //}

    //@JvmName("mapListDbModelToListEntityFolders")
    //fun mapListDbModelToListEntity(list: List<FolderDbModel>) = list.map {
    //    mapDbModelToEntity(it)
    //}

}