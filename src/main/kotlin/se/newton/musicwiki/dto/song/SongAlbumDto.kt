package se.newton.musicwiki.dto.song

import se.newton.musicwiki.persistence.models.AlbumSong


data class SongAlbumDto(
  val id: Long?,
  val name: String?,
  val track: Int?,
)

fun mapSongAlbumToDto(albumSong: AlbumSong): SongAlbumDto {

  return SongAlbumDto(
    id = albumSong.album?.id,
    name = albumSong.album?.name,
    track = albumSong.track,
  )
}

fun mapSongAlbumToDtos(albums: List<AlbumSong>): List<SongAlbumDto> {
  return albums.map { mapSongAlbumToDto(it) }
}