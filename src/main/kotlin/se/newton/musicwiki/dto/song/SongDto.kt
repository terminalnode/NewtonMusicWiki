package se.newton.musicwiki.dto.song

import se.newton.musicwiki.persistence.models.Song


data class SongDto(
  val id: Long?,
  val name: String?,
  val artists: List<SongArtistDto> = mutableListOf(),
  val albums: List<SongAlbumDto> = mutableListOf()
)

data class SongListDto(
  val artists: List<SongDto>
)

fun mapSongToDto(song: Song): SongDto {
  return SongDto(
    id = song.id,
    name = song.name,
    artists = mapSongArtistToDtos(song.artists),
    //albums = mapSongAlbumToDtos(song.albums),
  )
}

fun mapSongToDtos(song: List<Song>): List<SongDto> {
  return song.map { mapSongToDto(it) }
}