package se.newton.musicwiki.dto.album

import se.newton.musicwiki.persistence.models.Artist

data class AlbumArtistDto(
  val id: Long?,
  val name: String?,
  val longitude: Double?,
  val latitude: Double?,
)

fun mapAlbumArtistToDto(artist: Artist): AlbumArtistDto {
  return AlbumArtistDto(
    id = artist.id,
    name = artist.name,
    longitude = artist.longitude,
    latitude = artist.latitude,
  )
}

fun mapAlbumArtistsToDtos(artists: List<Artist>): List<AlbumArtistDto> {
  return artists.map { mapAlbumArtistToDto(it) }
}