package com.example.moviespetapp.data.mapper

import com.example.moviespetapp.data.network.model.GenreDto
import com.example.moviespetapp.data.network.model.MovieInfoDto
import com.example.moviespetapp.data.network.model.TrailerDto
import com.example.moviespetapp.domain.Genre
import com.example.moviespetapp.domain.Movie
import com.example.moviespetapp.domain.Trailer

object MovieMapper {

    fun mapEntityToDtoModel(entity: Movie) = MovieInfoDto(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        year = entity.year,
        poster = entity.poster,
        rating = entity.rating
    )

    fun mapEntityToDtoModel(entity: Genre) = GenreDto(
        name = entity.name,
        slug = entity.slug
    )

    fun mapDtoToEntity(dto: MovieInfoDto) = Movie(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        year = dto.year,
        poster = dto.poster,
        rating = dto.rating,
        trailers = mapTrailersListDtoToListEntity(dto.videos?.trailers)
    )

    fun mapDtoToEntity(dto: GenreDto) = Genre(
        name = dto.name,
        slug = dto.slug
    )

    fun mapDtoToEntity(dto: TrailerDto) = Trailer(
        url = dto.url,
        name = dto.name,
        site = dto.site,
        type = dto.type,
    )

    fun mapGenresListDtoToListEntity(listDto: List<GenreDto>): List<Genre> {
        val listEntityFull = listDto.map { mapDtoToEntity(it) }
        val listToRemove = listOf(
            Genre("для взрослых", "dlya-vzroslyh"),
            Genre("концерт", "koncert"),
            Genre("короткометражка", "korotkometrazhka"),
            Genre("музыка", "muzyka"),
            Genre("новости", "novosti"),
            Genre("реальное ТВ", "realnoe-TV"),
            Genre("ток-шоу", "tok-shou"),
            Genre("церемония", "ceremoniya"),
            Genre("игра", "igra"),
            Genre("фильм-нуар", "film-nuar"),
        )
        return listEntityFull.minus(listToRemove.toSet())
    }

    private fun mapTrailersListDtoToListEntity(listDto: List<TrailerDto>?): List<Trailer>? {
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