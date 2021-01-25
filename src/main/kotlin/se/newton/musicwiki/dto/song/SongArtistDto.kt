package se.newton.musicwiki.dto.song

import se.newton.musicwiki.persistence.models.Artist


data class SongArtistDto(
  val id: Long?,
  val name: String?
)

data class SongArtistListDto(
  val albums: List<SongArtistDto>
)

fun mapSongArtistToDto(artist: Artist): SongArtistDto {
  return SongArtistDto(
    id = artist.id,
    name = artist.name
  )
}

fun mapSongArtistToDtos(artists: List<Artist>): List<SongArtistDto> {
  return artists.map { mapSongArtistToDto(it) }
}