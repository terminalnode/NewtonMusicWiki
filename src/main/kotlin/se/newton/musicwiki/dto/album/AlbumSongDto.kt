package se.newton.musicwiki.dto.album

import se.newton.musicwiki.persistence.models.AlbumSong

data class AlbumSongDto(
  val id: Long?,
  val name: String?,
  val track: Int?
)

fun mapAlbumSongToDto(albumSong: AlbumSong): AlbumSongDto {
  return AlbumSongDto(
    id = albumSong.song?.id,
    name = albumSong.song?.name,
    track = albumSong.track
  )
}

fun mapAlbumSongsToDtos(songs: List<AlbumSong>): List<AlbumSongDto> {
  return songs.map { mapAlbumSongToDto(it) }
}

