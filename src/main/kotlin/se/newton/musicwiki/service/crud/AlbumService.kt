package se.newton.musicwiki.service.crud

import org.springframework.stereotype.Service
import se.newton.musicwiki.persistence.models.Album

@Service
interface AlbumService {
    fun upsert(album: Album): Album
    fun findById(id: Long): Album
    fun deleteById(id: Long)
}