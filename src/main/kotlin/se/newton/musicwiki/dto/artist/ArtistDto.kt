package se.newton.musicwiki.dto.artist

import se.newton.musicwiki.persistence.enums.ArtistType
import se.newton.musicwiki.persistence.models.Artist

data class ArtistDto(
    val id: Long?,
    val name: String?,
    val artistType: ArtistType?,
    val songs: List<ArtistSongDto> = mutableListOf(),
    val albums: List<ArtistAlbumDto> = mutableListOf()
)

data class ArtistListDto(
    val artists: List<ArtistDto>
)

fun mapArtistToDto(artist: Artist): ArtistDto {
    return ArtistDto(
        id = artist.id,
        name = artist.name,
        artistType = artist.artistType,
        songs = mapArtistSongsToDtos(artist.songs),
        albums = mapArtistAlbumsToDtos(artist.albums),
    )
}

fun mapArtistsToDtos(artists: List<Artist>): List<ArtistDto> {
    return artists.map { mapArtistToDto(it) }
}


