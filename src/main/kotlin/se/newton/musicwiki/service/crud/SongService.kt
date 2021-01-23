package se.newton.musicwiki.service.crud

import org.springframework.stereotype.Service
import se.newton.musicwiki.persistence.models.Song

@Service
interface SongService {
    fun upsert(song: Song): Song
    fun findById(id: Long): Song
    fun deleteById(id: Long)
}