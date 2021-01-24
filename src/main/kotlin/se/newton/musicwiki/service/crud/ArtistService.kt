package se.newton.musicwiki.service.crud

import se.newton.musicwiki.persistence.models.Artist

interface ArtistService {
    fun upsert(artist: Artist): Artist
    fun findById(id: Long): Artist
    fun deleteById(id: Long)
}