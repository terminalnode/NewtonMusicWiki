package se.newton.musicwiki.service.crud

import se.newton.musicwiki.persistence.enums.ArtistType
import se.newton.musicwiki.persistence.models.Artist

interface ArtistService {
  fun create(artist: Artist): Artist
  fun update(artist: Artist): Artist
  fun findById(id: Long): Artist
  fun findByIds(vararg ids: Long) : List<Artist>
  fun findByType(artistType: ArtistType) : List<Artist>
  fun deleteById(id: Long)
  fun findAll(): List<Artist>
  fun removeSongFromArtist(artistId: Long, songId: Long): Artist
  fun addSongToArtist(artistId: Long, songId: Long): Artist
  fun removeAlbumFromArtist(artistId: Long, albumId: Long): Artist
  fun addAlbumToArtist(artistId: Long, albumId: Long): Artist
}