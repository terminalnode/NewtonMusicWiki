package se.newton.musicwiki.dto.album

import se.newton.musicwiki.persistence.models.AlbumSong
import se.newton.musicwiki.persistence.models.Song


data class AlbumSongDto(
  val id: Long?,
  val name: String?,
  val track: Int?
)

data class AlbumSongListDto(
  val albums: List<AlbumSongDto>
)

fun mapAlbumSongToDto(song: Song, albumSong: AlbumSong): AlbumSongDto {
  return AlbumSongDto(
    id = song.id,
    name = song.name,
    track = albumSong.track
  )
}

