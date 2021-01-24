package se.newton.musicwiki.dto

import se.newton.musicwiki.persistence.models.Song

data class ArtistSongDto(
    val id: Long,
    val name: String
)

data class ArtistSongListDto(
    val songs: List<ArtistSongDto>
)

fun mapArtistSongToDto(song: Song): ArtistSongDto? {
    val id = song.id ?: return null
    val name = song.name ?: return null
    return ArtistSongDto(
        id = id,
        name = name
    )
}

fun mapArtistSongsToDtos(songs: List<Song>): List<ArtistSongDto> {
    return songs.mapNotNull { mapArtistSongToDto(it) }
}