package se.newton.musicwiki.service.crud

import org.springframework.stereotype.Service
import se.newton.musicwiki.persistence.models.Artist

@Service
interface ArtistService {
    fun upsert(artist: Artist): Artist
    fun findById(id: Long): Artist
    fun deleteById(id: Long)
}