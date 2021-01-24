package se.newton.musicwiki.persistence.repositories

import org.springframework.data.jpa.repository.JpaRepository
import se.newton.musicwiki.persistence.models.Album

interface AlbumRepository : JpaRepository<Album, Long> {
    fun findByIdIn(ids: Collection<Long?>): List<Album>
}