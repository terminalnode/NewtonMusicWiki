package se.newton.musicwiki.dto.artist

import se.newton.musicwiki.persistence.models.Song

data class ArtistSongDto(
  val id: Long?,
  val name: String?
)

data class ArtistSongListDto(
  val songs: List<ArtistSongDto>
)

fun mapArtistSongToDto(song: Song): ArtistSongDto {
  return ArtistSongDto(
    id = song.id,
    name = song.name
  )
}

fun mapArtistSongsToDtos(songs: List<Song>): List<ArtistSongDto> {
  return songs.map { mapArtistSongToDto(it) }
}