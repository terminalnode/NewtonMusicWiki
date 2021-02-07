package se.newton.musicwiki.service.crud

import se.newton.musicwiki.persistence.enums.ArtistType
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song

interface ArtistService {
  fun create(artist: Artist): Artist
  fun update(artist: Artist): Artist
  fun findById(id: Long): Artist
  fun findByIds(vararg ids: Long) : List<Artist>
  fun findByType(artistType: ArtistType) : List<Artist>
  fun deleteById(id: Long)
  fun findAll(): List<Artist>
  fun addSongsToArtist(artistId: Long, songs: List<Song>): List<Song>
}