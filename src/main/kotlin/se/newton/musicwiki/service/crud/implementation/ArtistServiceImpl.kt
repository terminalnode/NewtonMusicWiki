package se.newton.musicwiki.service.crud.implementation

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import se.newton.musicwiki.persistence.enums.ArtistType
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.repositories.AlbumRepository
import se.newton.musicwiki.persistence.repositories.ArtistRepository
import se.newton.musicwiki.persistence.repositories.SongRepository
import se.newton.musicwiki.service.crud.ArtistService
import javax.persistence.EntityNotFoundException

@Service
class ArtistServiceImpl(
  val artistRepository: ArtistRepository,
  val songRepository: SongRepository,
  val albumRepository: AlbumRepository,
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

  override fun findByType(type: ArtistType): List<Artist> {
    return artistRepository.findByArtistType(type)
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

  override fun removeSongFromArtist(artistId: Long, songId: Long): Artist {
    val artist = artistRepository.findById(artistId).orElseThrow();
    artist.songs.removeIf { it.id == songId };
    return artistRepository.save(artist)
  }

  override fun addSongToArtist(artistId: Long, songId: Long): Artist {
    val artist = artistRepository.findById(artistId).orElseThrow();
    val song = songRepository.findById(songId).orElseThrow();
    if (!artist.songs.contains(song)) {
      artist.songs.add(song)
    }
    return artistRepository.save(artist)
  }

  override fun removeAlbumFromArtist(artistId: Long, albumId: Long): Artist {
    val artist = artistRepository.findById(artistId).orElseThrow();
    artist.albums.removeIf { it.id == albumId };
    return artistRepository.save(artist)
  }

  override fun addAlbumToArtist(artistId: Long, albumId: Long): Artist {
    val artist = artistRepository.findById(artistId).orElseThrow();
    val album = albumRepository.findById(albumId).orElseThrow();
    if (!artist.albums.contains(album)) {
      artist.albums.add(album)
    }
    return artistRepository.save(artist)
  }
}