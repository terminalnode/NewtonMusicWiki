package se.newton.musicwiki.persistence.repositories

import org.springframework.data.jpa.repository.JpaRepository
import se.newton.musicwiki.persistence.models.Song

interface SongRepository : JpaRepository<Song, Long> {
  fun findByIdIn(ids: Collection<Long?>): List<Song>
}