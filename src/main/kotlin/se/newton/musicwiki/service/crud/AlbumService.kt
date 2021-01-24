package se.newton.musicwiki.service.crud

import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.persistence.models.AlbumSong
import se.newton.musicwiki.persistence.models.Artist

interface AlbumService {
    fun create(album: Album): Album
    fun update(album: Album): Album
    fun addSongsToAlbum(albumId: Long, vararg songs: AlbumSong) : Album
    fun addSongAndSongArtistsToAlbum(albumId: Long, vararg songs: AlbumSong) : Album
    fun addArtistsToAlbum(albumId: Long, vararg artists: Artist) : Album
    fun findById(id: Long): Album
    fun deleteById(id: Long)
    fun findAll(): List<Album>
}