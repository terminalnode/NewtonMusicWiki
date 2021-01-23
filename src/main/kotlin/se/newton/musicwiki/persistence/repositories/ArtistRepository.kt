package se.newton.musicwiki.persistence.repositories

import org.springframework.data.jpa.repository.JpaRepository
import se.newton.musicwiki.persistence.models.Artist

interface ArtistRepository : JpaRepository<Artist, Long>