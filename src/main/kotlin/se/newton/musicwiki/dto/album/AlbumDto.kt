package se.newton.musicwiki.dto.album

import se.newton.musicwiki.dto.album.*
import se.newton.musicwiki.persistence.models.Album

data class AlbumDto(
    val id: Long?,
    val name: String?,
    val songs: List<AlbumSongDto> = mutableListOf(),
    val artists: List<AlbumArtistDto> = mutableListOf()
  )

data class ArtistListDto(
  val albums: List<AlbumDto>
)

fun mapAlbumToDto(album: Album): AlbumDto {
  return AlbumDto(
    id = album.id,
    name = album.name,
    songs = mapAlbumSongsToDtos(album.songs),
    artists = mapAlbumArtistsToDtos(album.artists),
  )
}

fun mapAlbumsToDtos(albums: List<Album>): List<AlbumDto> {
  return albums.map { mapAlbumToDto(it) }
}