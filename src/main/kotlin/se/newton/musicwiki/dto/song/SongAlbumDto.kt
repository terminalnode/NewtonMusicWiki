package se.newton.musicwiki.dto.song

import se.newton.musicwiki.persistence.models.Album


data class SongAlbumDto(
  val id: Long?,
  val name: String?
)

data class SongAlbumListDto(
  val albums: List<SongAlbumDto>
)

fun mapSongAlbumToDto(album: Album): SongAlbumDto {
  return SongAlbumDto(
    id = album.id,
    name = album.name
  )
}

fun mapSongAlbumToDtos(albums: List<Album>): List<SongAlbumDto> {
  return albums.map { mapSongAlbumToDto(it) }
}