package se.newton.musicwiki.service.crud.implementation

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.persistence.models.AlbumSong
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.repositories.AlbumRepository
import se.newton.musicwiki.persistence.repositories.SongRepository
import se.newton.musicwiki.service.crud.AlbumService
import java.util.*
import java.util.stream.Collectors
import javax.persistence.EntityNotFoundException

@Service
class AlbumServiceImpl(
  val albumRepository: AlbumRepository,
  val songRepository: SongRepository
) : AlbumService {
  private fun getAlbum(albumId: Long): Album {
    return albumRepository.findByIdOrNull(albumId)
      ?: throw EntityNotFoundException("No album by that id exists")
  }

  override fun create(album: Album): Album {
    album.id = 0;

    // Replace existing songs with entities
    val songIds = album.songs.mapNotNull { it.id }
    val existingSongs = songRepository.findByIdIn(songIds)

    album.songs.forEach { originalSong ->
      if (existingSongs.contains(originalSong.song)) {
        val existingSong = existingSongs.first { it == originalSong.song }
        originalSong.song = existingSong
      } else {
        originalSong.song?.id = 0
      }
    }
    album.songs.forEach { it.album = album }

    return albumRepository.save(album)
  }

  override fun update(album: Album): Album {
    val albumId = album.id
      ?: throw EntityNotFoundException("The provided album does not have an id")

    if (!albumRepository.existsById(albumId)) {
      throw EntityNotFoundException("No album by that id exists, please create the album first")
    }

    return albumRepository.save(album)
  }

  override fun addSongsToAlbum(albumId: Long, vararg songs: AlbumSong): Album {
    val album = getAlbum(albumId)
    album.songs.addAll(songs)
    return albumRepository.save(album)
  }

  override fun addSongAndSongArtistsToAlbum(albumId: Long, vararg songs: AlbumSong): Album {
    val album = getAlbum(albumId)
    album.songs.addAll(songs)
    val songArtists: List<Artist> = Arrays.stream(songs)
      .map { it?.song?.artists }
      .flatMap { it?.stream() }
      .filter { it != null }
      .collect(Collectors.toList())
    album.artists.addAll(songArtists)
    return albumRepository.save(album)
  }

  override fun addArtistsToAlbum(albumId: Long, vararg artists: Artist): Album {
    val album = getAlbum(albumId)
    album.artists.addAll(artists)
    return albumRepository.save(album)
  }

  override fun findById(id: Long): Album {
    return getAlbum(id)
  }

  override fun findByIds(vararg ids: Long): List<Album> {
    return albumRepository.findByIdIn(ids.asList())
  }

  override fun deleteById(id: Long) {
    if (!albumRepository.existsById(id)) {
      throw EntityNotFoundException("No album by that id exists")
    }
    albumRepository.deleteById(id)
  }

  override fun findAll(): List<Album> {
    return albumRepository.findAll()
  }
}