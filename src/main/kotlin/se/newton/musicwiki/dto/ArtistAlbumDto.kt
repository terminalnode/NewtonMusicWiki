package se.newton.musicwiki.dto

import se.newton.musicwiki.persistence.models.Album

data class ArtistAlbumDto(
    val id: Long,
    val name: String
)

data class ArtistAlbumListDto(
    val albums: List<ArtistAlbumDto>
)

fun mapArtistAlbumToDto(album: Album): ArtistAlbumDto? {
    val id = album.id ?: return null
    val name = album.name ?: return null
    return ArtistAlbumDto(id, name)
}

fun mapArtistAlbumsToDtos(albums: Collection<Album>): List<ArtistAlbumDto> {
    return albums.mapNotNull { mapArtistAlbumToDto(it) }
}