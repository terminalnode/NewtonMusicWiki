package se.newton.musicwiki.service.crud

import se.newton.musicwiki.persistence.models.Song

interface SongService {
    fun upsert(song: Song): Song
    fun findById(id: Long): Song
    fun deleteById(id: Long)
}