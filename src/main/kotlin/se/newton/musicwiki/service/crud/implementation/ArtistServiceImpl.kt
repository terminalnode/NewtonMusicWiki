package se.newton.musicwiki.service.crud.implementation

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song
import se.newton.musicwiki.persistence.repositories.ArtistRepository
import se.newton.musicwiki.persistence.repositories.SongRepository
import se.newton.musicwiki.service.crud.ArtistService
import javax.persistence.EntityNotFoundException

@Service
class ArtistServiceImpl(
  val artistRepository: ArtistRepository,
  val songRepository: SongRepository
) : ArtistService {

  override fun create(artist: Artist): Artist {
    artist.id = 0;

    // Only save primary fields when creating an artist
    artist.albums = mutableListOf()
    artist.songs = mutableListOf()

    return artistRepository.save(artist)
  }

  override fun update(artist: Artist): Artist {
    val artistId = artist.id
      ?: throw EntityNotFoundException("The provided artist does not have an id")

    if (!artistRepository.existsById(artistId)) {
      throw EntityNotFoundException("No artist by that id exists, please create the artist first")
    }

    return artistRepository.save(artist)
  }

  override fun findById(id: Long): Artist {
    return getArtist(id)
  }

  override fun findByIds(vararg ids: Long): List<Artist> {
    return artistRepository.findByIdIn(ids.asList())
  }

  private fun getArtist(artistId: Long): Artist {
    return artistRepository.findByIdOrNull(artistId)
      ?: throw EntityNotFoundException("No artist by that id exists")
  }

  override fun deleteById(id: Long) {
    if (!artistRepository.existsById(id)) {
      throw EntityNotFoundException("No artist by that id exists")
    }
    artistRepository.deleteById(id)
  }

  override fun findAll(): List<Artist> {
    return artistRepository.findAll()
  }

  override fun addSongsToArtist(artistId: Long, songs: List<Song>): List<Song> {
    songs.forEach { it.id = 0 }
    val savedSongs = songRepository.saveAll(songs)

    val artist = getArtist(artistId)
    artist.songs.addAll(savedSongs)
    artistRepository.save(artist).songs
    return savedSongs
  }
}