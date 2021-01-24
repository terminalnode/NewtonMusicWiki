package se.newton.musicwiki.dto

import se.newton.musicwiki.persistence.enums.ArtistType
import se.newton.musicwiki.persistence.models.Artist

data class ArtistDto(
    val id: Long,
    val name: String,
    val artistType: ArtistType,
    val songs: List<ArtistSongDto> = mutableListOf(),
    val albums: List<ArtistAlbumDto> = mutableListOf()
)

data class ArtistListDto(
    val artists: List<ArtistDto>
)

fun mapArtistToDto(artist: Artist): ArtistDto? {
    val id = artist.id ?: return null
    val name = artist.name ?: return null
    val artistType = artist.artistType ?: return null
    return ArtistDto(
        id = id,
        name = name,
        artistType = artistType,
        songs = mapArtistSongsToDtos(artist.songs),
        albums = mapArtistAlbumsToDtos(artist.albums),
    )
}

fun mapArtistsToDtos(artists: List<Artist>): List<ArtistDto> {
    return artists.mapNotNull { mapArtistToDto(it) }
}
