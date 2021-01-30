package se.newton.musicwiki.service.crud

import se.newton.musicwiki.persistence.models.Song

interface SongService {
  fun create(song: Song): Song
  fun update(song: Song): Song
  fun findById(id: Long?): Song
  fun findByIds(vararg ids: Long) : List<Song>
  fun existsById(id: Long?): Boolean
  fun findAll(): List<Song>
  fun deleteById(id: Long)
}