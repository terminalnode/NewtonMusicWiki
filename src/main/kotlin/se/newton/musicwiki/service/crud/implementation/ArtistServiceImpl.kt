package se.newton.musicwiki.service.crud.implementation

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.repositories.ArtistRepository
import se.newton.musicwiki.service.crud.ArtistService
import javax.persistence.EntityNotFoundException

@Service
class ArtistServiceImpl(
  val artistRepository: ArtistRepository
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
}