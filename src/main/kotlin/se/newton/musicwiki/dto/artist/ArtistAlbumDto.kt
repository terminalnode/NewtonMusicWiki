package se.newton.musicwiki.dto.artist

import se.newton.musicwiki.persistence.models.Album

data class ArtistAlbumDto(
    val id: Long?,
    val name: String?
)

fun mapArtistAlbumToDto(album: Album): ArtistAlbumDto {
    return ArtistAlbumDto(
        id = album.id,
        name = album.name
    )
}

fun mapArtistAlbumsToDtos(albums: Collection<Album>): List<ArtistAlbumDto> {
    return albums.map { mapArtistAlbumToDto(it) }
}