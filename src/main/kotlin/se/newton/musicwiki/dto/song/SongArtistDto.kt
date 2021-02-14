package se.newton.musicwiki.dto.song

import se.newton.musicwiki.persistence.enums.ArtistType
import se.newton.musicwiki.persistence.models.Artist

data class SongArtistDto(
  val id: Long?,
  val name: String?,
  val type: ArtistType?,
  val longitude: Double?,
  val latitude: Double?,
)

fun mapSongArtistToDto(artist: Artist): SongArtistDto {
  return SongArtistDto(
    id = artist.id,
    name = artist.name,
    type = artist.artistType,
    longitude = artist.longitude,
    latitude = artist.latitude,
  )
}

fun mapSongArtistToDtos(artists: List<Artist>): List<SongArtistDto> {
  return artists.map { mapSongArtistToDto(it) }
}