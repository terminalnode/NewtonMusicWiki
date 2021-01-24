package se.newton.musicwiki.service.crud

import se.newton.musicwiki.persistence.models.Artist

interface ArtistService {
    fun create(artist: Artist): Artist
    fun update(artist: Artist): Artist
    fun findById(id: Long): Artist
    fun deleteById(id: Long)
    fun findAll(): List<Artist>
}